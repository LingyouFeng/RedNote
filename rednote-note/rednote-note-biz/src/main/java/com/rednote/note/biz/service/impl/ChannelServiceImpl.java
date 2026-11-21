package com.rednote.note.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.rednote.common.response.Result;
import com.rednote.note.biz.domain.dataobject.ChannelDO;
import com.rednote.note.biz.domain.mapper.ChannelDOMapper;
import com.rednote.note.biz.model.vo.FindChannelRspVO;
import com.rednote.note.biz.service.ChannelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 频道业务
 *
 * @author Jason Feng
 * @since 2026-11-21 16:38
 */
@Service
@Slf4j
public class ChannelServiceImpl implements ChannelService {

    @Resource
    private ChannelDOMapper channelDOMapper;

    /**
     * 查询所有频道
     *
     * @return
     */
    @Override
    public Result<List<FindChannelRspVO>> findChannelList() {
        // TODO: 加二级缓存

        List<ChannelDO> channelDOS = channelDOMapper.selectAll();

        List<FindChannelRspVO> channelRspVOS = Lists.newArrayList();

        // 默认添加一个 “全部” 分类
        // FindChannelRspVO allChannel = FindChannelRspVO.builder()
        //         .id(0L)
        //         .name("全部")
        //         .build();
        // channelRspVOS.add(allChannel);

        if (CollUtil.isNotEmpty(channelDOS)) {
            CollUtil.addAll(channelRspVOS, channelDOS.stream()
                    .map(channelDO -> FindChannelRspVO.builder()
                            .id(channelDO.getId())
                            .name(channelDO.getName())
                            .build())
                    .toList());
        }

        return Result.success(channelRspVOS);
    }
}
