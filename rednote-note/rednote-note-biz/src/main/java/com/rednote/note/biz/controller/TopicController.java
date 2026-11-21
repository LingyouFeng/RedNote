package com.rednote.note.biz.controller;


import com.rednote.common.response.Result;
import com.rednote.note.biz.model.vo.FindTopicListReqVO;
import com.rednote.note.biz.model.vo.FindTopicRspVO;
import com.rednote.note.biz.service.TopicService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 话题
 *
 * @author Jason Feng
 * @since 2026-11-21 17:12
 */
@RestController
@RequestMapping("/topic")
@Slf4j
public class TopicController {

    @Resource
    private TopicService topicService;

    @PostMapping(value = "/list")
    public Result<List<FindTopicRspVO>> findTopicList(@Validated @RequestBody FindTopicListReqVO findTopicListReqVO) {
        return topicService.findTopicList(findTopicListReqVO);
    }

}
