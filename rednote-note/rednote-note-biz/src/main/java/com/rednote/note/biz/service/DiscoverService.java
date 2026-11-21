package com.rednote.note.biz.service;


import com.rednote.common.response.PageResult;
import com.rednote.note.biz.model.vo.FindDiscoverNotePageListReqVO;
import com.rednote.note.biz.model.vo.FindDiscoverNoteRspVO;

/**
 * 发现页业务
 *
 * @author Jason Feng
 * @since 2026-11-21 16:29
 */
public interface DiscoverService {

    PageResult<FindDiscoverNoteRspVO> findNoteList(FindDiscoverNotePageListReqVO findDiscoverNoteListReqVO);
}
