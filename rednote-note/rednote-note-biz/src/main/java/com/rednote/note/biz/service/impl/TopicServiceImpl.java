package com.rednote.note.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.rednote.common.response.Result;
import com.rednote.note.biz.domain.dataobject.TopicDO;
import com.rednote.note.biz.domain.mapper.TopicDOMapper;
import com.rednote.note.biz.model.vo.FindTopicListReqVO;
import com.rednote.note.biz.model.vo.FindTopicRspVO;
import com.rednote.note.biz.service.TopicService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 频道业务
 *
 * @author Jason Feng
 * @since 2026-11-21 16:57
 */
@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicDOMapper topicDOMapper;

    @Override
    public Result<List<FindTopicRspVO>> findTopicList(FindTopicListReqVO findTopicListReqVO) {
        String keyword = findTopicListReqVO.getKeyword();

        List<TopicDO> topicDOS = topicDOMapper.selectByLikeName(keyword);

        List<FindTopicRspVO> findTopicRspVOS = null;
        if (CollUtil.isNotEmpty(topicDOS)) {
            findTopicRspVOS = topicDOS.stream()
                    .map(topicDO -> FindTopicRspVO.builder()
                            .id(topicDO.getId())
                            .name(topicDO.getName())
                            .build())
                    .toList();
        }

        return Result.success(findTopicRspVOS);
    }
}
