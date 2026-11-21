package com.rednote.note.biz.service;

import com.rednote.common.response.PageResult;
import com.rednote.note.biz.model.vo.FindProfileNotePageListReqVO;
import com.rednote.note.biz.model.vo.FindProfileNoteRspVO;

/**
 * 个人主页业务
 *
 * @author Jason Feng
 * @since 2026-11-21 16:29
 */
public interface ProfileService {

    PageResult<FindProfileNoteRspVO> findNoteList(FindProfileNotePageListReqVO findProfileNotePageListReqVO);
}
