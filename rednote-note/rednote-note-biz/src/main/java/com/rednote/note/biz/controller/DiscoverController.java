package com.rednote.note.biz.controller;

import com.rednote.common.response.PageResult;
import com.rednote.note.biz.model.vo.FindDiscoverNotePageListReqVO;
import com.rednote.note.biz.model.vo.FindDiscoverNoteRspVO;
import com.rednote.note.biz.service.DiscoverService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发现页
 *
 * @author Jason Feng
 * @since 2026-11-21 17:12
 */
@RestController
@RequestMapping("/discover")
@Slf4j
public class DiscoverController {

    @Resource
    private DiscoverService discoverService;

    @PostMapping(value = "/note/list")
    public PageResult<FindDiscoverNoteRspVO> findNoteList(@RequestBody @Valid FindDiscoverNotePageListReqVO findDiscoverNoteListReqVO) {
        return discoverService.findNoteList(findDiscoverNoteListReqVO);
    }

}
