package com.rednote.note.biz.controller;


import com.rednote.common.response.PageResult;
import com.rednote.note.biz.model.vo.FindProfileNotePageListReqVO;
import com.rednote.note.biz.model.vo.FindProfileNoteRspVO;
import com.rednote.note.biz.service.ProfileService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人主页
 *
 * @author Jason Feng
 * @since 2026-11-21 17:12
 */
@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    @Resource
    private ProfileService profileService;

    @PostMapping(value = "/note/list")
    public PageResult<FindProfileNoteRspVO> findNoteList(@RequestBody @Valid FindProfileNotePageListReqVO findProfileNotePageListReqVO) {
        return profileService.findNoteList(findProfileNotePageListReqVO);
    }

}
