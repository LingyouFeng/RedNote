package com.redshop.product.biz.service.impl;

import cn.hutool.core.lang.Singleton;
import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.rednote.common.exception.BusinessException;
import com.rednote.common.response.Result;
import com.redshop.product.biz.constant.MQConstants;
import com.redshop.product.biz.constant.RedisKeyConstants;
import com.redshop.product.biz.domain.dataobject.ProductCategoryDO;
import com.redshop.product.biz.domain.mapper.ProductCategoryDOMapper;
import com.redshop.product.biz.enums.ProductResultCodeEnum;
import com.redshop.product.biz.model.dto.UpdateProductCategoryMqDTO;
import com.redshop.product.biz.model.vo.FindProductCategoryRspVO;
import com.redshop.product.biz.model.vo.UpdateProductCategoryReqVO;
import com.redshop.product.biz.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 商品服务类
 *
 * @author Jason Feng
 * @since 2026-11-26 22:58
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private ProductCategoryDOMapper categoryDOMapper;

    /**
     * 商品分类信息本地缓存，极高热点读取数据，不设置过期时间
     */
    private static final Cache<Long, List<FindProductCategoryRspVO>> CATEGORY_LOCAL_CACHE = Caffeine.newBuilder()
            .initialCapacity(200) // 设置初始容量为 200 个条目
            .maximumSize(200) // 设置缓存的最大容量为 200 个条目
            .recordStats()  // 开启统计，便于观察命中率
            .build();

    /**
     * 本实例 L1 已应用到的分类数据版本。
     * -1 表示尚未加载过，收到任何版本的消息都应处理。
     */
    private final AtomicLong localCategoryVersion = new AtomicLong(-1L);

    private final static String UPDATE_CATEGORY_AND_VERSION_LUA = "/lua/update_category_and_version.lua";

    /**
     * 获取商品顶层分类
     */
    @Override
    public Result<?> getTopCategories() {
        return Result.success(getCategoriesByParentId(0L));
    }

    /**
     * 根据 parentId 获取商品子级分类
     */
    @Override
    public Result<?> getChildrenCategories(Long id) {
        return Result.success(getCategoriesByParentId(id));
    }

    private List<FindProductCategoryRspVO> getCategoriesByParentId(Long parentId) {
        log.debug("查询商品分类开始, parentId={}", parentId);

        // 1. 本地缓存（L1）
        List<FindProductCategoryRspVO> local = CATEGORY_LOCAL_CACHE.getIfPresent(parentId);
        if (local != null) {
            log.debug("本地缓存命中, parentId={}, size={}", parentId, local.size());
            return local;
        }
        log.debug("本地缓存未命中, parentId={}, 尝试查询 Redis", parentId);

        // 2. Redis（L2）
        String key = RedisKeyConstants.PRODUCT_CATEGORIES_KEY;
        if (redisTemplate.hasKey(key)) {
            String json = (String) redisTemplate.opsForHash().get(key, String.valueOf(parentId));

            List<FindProductCategoryRspVO> result = json != null
                    ? JSON.parseArray(json, FindProductCategoryRspVO.class)
                    : Collections.emptyList();
            log.debug("Redis 缓存命中, key={}, parentId={}, size={}", key, parentId, result.size());

            // 空列表也缓存，防止穿透到 DB
            CATEGORY_LOCAL_CACHE.put(parentId, result);
            return result;
        }

        // 3. 未命中，查 DB 全量，按 parentId 分组并直接转 VO
        log.info("本地缓存与 Redis 均未命中, 回源 DB 加载全量分类, parentId={}", parentId);
        List<ProductCategoryDO> all = categoryDOMapper.selectAll();
        Map<Long, List<FindProductCategoryRspVO>> voGrouped = buildVoGrouped(all);

        // 显式构造 Map<String, String>，与读取侧序列化完全对齐
        Map<String, String> cacheMap = voGrouped.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> String.valueOf(e.getKey()),
                        e -> JSON.toJSONString(e.getValue())
                ));

        // 4. 写入 redis 中
        redisTemplate.opsForHash().putAll(RedisKeyConstants.PRODUCT_CATEGORIES_KEY, cacheMap);
        // 5. 回填本地缓存
        voGrouped.forEach(CATEGORY_LOCAL_CACHE::put);

        // 当前 parentId 可能没有子节点，也缓存一个空列表避免反复穿透
        List<FindProductCategoryRspVO> result = voGrouped.getOrDefault(parentId, Collections.emptyList());
        CATEGORY_LOCAL_CACHE.put(parentId, result);
        return result;
    }

    /**
     * 全量 DO 按 parentId 分组并转 VO
     */
    private Map<Long, List<FindProductCategoryRspVO>> buildVoGrouped(List<ProductCategoryDO> all) {
        Map<Long, List<ProductCategoryDO>> doGrouped = all.stream()
                .sorted(Comparator.comparing(ProductCategoryDO::getSort))
                .collect(Collectors.groupingBy(ProductCategoryDO::getParentId));

        Set<Long> allParentIds = doGrouped.keySet();
        return doGrouped.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(item -> toVO(item, allParentIds))
                                .collect(Collectors.toList())
                ));
    }

    private FindProductCategoryRspVO toVO(ProductCategoryDO categoryDO, Set<Long> allParentIds) {
        return FindProductCategoryRspVO.builder()
                .id(categoryDO.getId())
                .name(categoryDO.getName())
                .image(categoryDO.getImage())
                .level(categoryDO.getLevel())
                .sort(categoryDO.getSort())
                .hasChildren(allParentIds.contains(categoryDO.getId()))
                .build();
    }

    @Override
    public Result<?> updateCategories(UpdateProductCategoryReqVO reqVO) {
        ProductCategoryDO categoryDO = categoryDOMapper.selectByPrimaryKey(reqVO.getId());
        if (categoryDO == null) {
            throw new BusinessException(ProductResultCodeEnum.CATEGORY_NOT_EXIST);
        }
        boolean isNeedUpdate = false;

        // 分类名称
        String name = reqVO.getName();
        if (!StringUtils.isEmpty(name) && !StringUtils.equals(categoryDO.getName(), name)) {
            isNeedUpdate = true;
            categoryDO.setName(name);
        }

        // 分类图片
        String image = reqVO.getImage();
        if (!StringUtils.isEmpty(image) && !StringUtils.equals(categoryDO.getImage(), image)) {
            isNeedUpdate = true;
            categoryDO.setImage(image);
        }

        // 分类顺序
        Integer sort = reqVO.getSort();
        if (!Objects.isNull(sort) && !Objects.equals(categoryDO.getSort(), sort)) {
            isNeedUpdate = true;
            categoryDO.setSort(sort);
        }

        if (isNeedUpdate) {
            categoryDOMapper.updateByPrimaryKeySelective(categoryDO);
            log.info("更新商品分类，categoryDO={}", JSON.toJSONString(categoryDO));
        }

        // 使用 lua 脚本更新 redis 中商品分类数据和版本号
        DefaultRedisScript<Long> updateProductCategoryScript = Singleton.get(UPDATE_CATEGORY_AND_VERSION_LUA, () -> {
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            // Lua 脚本路径
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(UPDATE_CATEGORY_AND_VERSION_LUA)));
            // 返回值类型
            redisScript.setResultType(Long.class);
            return redisScript;
        });

        // 全量重载 DB，重建所有分片
        List<ProductCategoryDO> all = categoryDOMapper.selectAll();
        Map<Long, List<FindProductCategoryRspVO>> voGrouped = buildVoGrouped(all);

        List<String> args = new ArrayList<>(voGrouped.size() * 2);
        voGrouped.forEach((parentId, list) -> {
            args.add(String.valueOf(parentId));
            args.add(JSON.toJSONString(list));
        });

        Long newVersion = redisTemplate.execute(
                updateProductCategoryScript,
                List.of(RedisKeyConstants.PRODUCT_CATEGORIES_VERSION_KEY,
                        RedisKeyConstants.PRODUCT_CATEGORIES_KEY),
                args.toArray()
        );
        log.info("商品分类 Redis 重建完成, newVersion={}", newVersion);

        // 发送 MQ 广播通知，通知各实例更新
        // 构建消息对象，并将 DTO 转成 Json 字符串设置到消息体中
        UpdateProductCategoryMqDTO mqDTO = UpdateProductCategoryMqDTO.builder()
                .id(categoryDO.getId())
                .name(categoryDO.getName())
                .image(categoryDO.getImage())
                .sort(categoryDO.getSort())
                .msgVersion(newVersion)
                .build();

        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(mqDTO))
                .build();

        // 异步发送 MQ 消息，提升接口响应速度
        rocketMQTemplate.asyncSend(MQConstants.TOPIC_DELETE_PRODUCT_CATEGORY_LOCAL_CACHE, message,
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("==> 【更新商品分类】MQ 发送成功，SendResult: {}", sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.error("==> 【更新商品分类】MQ 发送异常: ", throwable);
                    }
                }
        );

        return Result.success();
    }

    @Override
    public void refreshLocalCacheByVersion(Long msgVersion) {
        if (msgVersion == null || msgVersion <= localCategoryVersion.get()) {
            log.debug("跳过过期分类刷新消息, msgVersion={}, localVersion={}", msgVersion, localCategoryVersion.get());
            return;
        }

        // 以 Redis 当前数据为准重载，不信任消息体内容（消息可能乱序）
        Map<Object, Object> entries = redisTemplate.opsForHash()
                .entries(RedisKeyConstants.PRODUCT_CATEGORIES_KEY);

        Map<Long, List<FindProductCategoryRspVO>> voGrouped = new HashMap<>(entries.size());
        entries.forEach((k, v) -> voGrouped.put(
                Long.valueOf(k.toString()),
                JSON.parseArray(v.toString(), FindProductCategoryRspVO.class)));

        refreshLocalCache(voGrouped, msgVersion);
    }

    /**
     * 用给定分片数据整体替换本实例 L1，并推进本地版本号
     */
    private void refreshLocalCache(Map<Long, List<FindProductCategoryRspVO>> voGrouped, Long version) {
        CATEGORY_LOCAL_CACHE.invalidateAll();
        voGrouped.forEach(CATEGORY_LOCAL_CACHE::put);
        localCategoryVersion.updateAndGet(v -> Math.max(v, version));
        log.info("本实例 L1 已刷新, size={}, localVersion={}", voGrouped.size(), localCategoryVersion.get());
    }
}
