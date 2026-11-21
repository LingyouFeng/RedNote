package com.rednote.note.biz.controller;

import com.rednote.common.response.Result;
import com.rednote.note.biz.model.vo.FindChannelRspVO;
import com.rednote.note.biz.service.ChannelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 频道
 *
 * @author Jason Feng
 * @since 2026-11-21 17:12
 */
@RestController
@RequestMapping("/channel")
@Slf4j
public class ChannelController {

    @Resource
    private ChannelService channelService;

    @PostMapping(value = "/list")
    public Result<List<FindChannelRspVO>> findChannelList() {
        return channelService.findChannelList();
    }

}
