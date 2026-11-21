package com.rednote.search.biz.service;

import com.rednote.common.response.PageResult;
import com.rednote.common.response.Result;
import com.rednote.search.api.dto.RebuildNoteDocumentReqDTO;
import com.rednote.search.biz.model.vo.SearchNoteReqVO;
import com.rednote.search.biz.model.vo.SearchNoteRspVO;

/**
 * 笔记搜索业务
 *
 * @author Jason Feng
 * @since 2026-03-22 16:31
 */
public interface NoteService {

    /**
     * 搜索笔记
     * @param searchNoteReqVO
     * @return
     */
    PageResult<SearchNoteRspVO> searchNote(SearchNoteReqVO searchNoteReqVO);

    /**
     * 重建笔记文档
     * @param rebuildNoteDocumentReqDTO
     * @return
     */
    Result<Long> rebuildDocument(RebuildNoteDocumentReqDTO rebuildNoteDocumentReqDTO);
}
