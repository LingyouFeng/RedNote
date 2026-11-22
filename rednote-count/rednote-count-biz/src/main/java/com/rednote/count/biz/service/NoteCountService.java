package com.rednote.count.biz.service;

import com.rednote.common.response.Result;
import com.rednote.count.api.dto.FindNoteCountsByIdRspDTO;
import com.rednote.count.api.dto.FindNoteCountsByIdsReqDTO;

import java.util.List;

/**
 * 笔记计数业务
 *
 * @author Jason Feng
 * @since 2026-11-22 11:48
 */
public interface NoteCountService {
    /**
     * 批量查询笔记计数
     * @param findNoteCountsByIdsReqDTO
     * @return
     */
    Result<List<FindNoteCountsByIdRspDTO>> findNotesCountData(FindNoteCountsByIdsReqDTO findNoteCountsByIdsReqDTO);
}
