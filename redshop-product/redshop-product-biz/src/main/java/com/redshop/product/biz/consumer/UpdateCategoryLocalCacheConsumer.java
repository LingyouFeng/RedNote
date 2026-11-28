package com.redshop.product.biz.consumer;

import com.alibaba.fastjson.JSON;
import com.redshop.product.biz.constant.MQConstants;
import com.redshop.product.biz.model.dto.UpdateProductCategoryMqDTO;
import com.redshop.product.biz.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jason Feng
 * @since 2026-11-28 22:10
 */
@Slf4j
@Component
@RocketMQMessageListener(
        consumerGroup = "redshop_group_" + MQConstants.TOPIC_DELETE_PRODUCT_CATEGORY_LOCAL_CACHE,   // Group
        topic = MQConstants.TOPIC_DELETE_PRODUCT_CATEGORY_LOCAL_CACHE   // 消费的主题 Topic
)
public class UpdateCategoryLocalCacheConsumer implements RocketMQListener<String> {

    @Resource
    private ProductService productService;

    @Override
    public void onMessage(String payload) {
        log.info("## 收到更新本地商品分类消息");
        UpdateProductCategoryMqDTO dto = JSON.parseObject(payload, UpdateProductCategoryMqDTO.class);
        productService.refreshLocalCacheByVersion(dto.getMsgVersion());
    }
}