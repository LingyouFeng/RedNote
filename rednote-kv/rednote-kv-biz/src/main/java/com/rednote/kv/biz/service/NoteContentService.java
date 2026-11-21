package com.rednote.kv.biz.service;

import com.rednote.common.response.Result;
import com.rednote.kv.api.dto.req.AddNoteContentReqDTO;
import com.rednote.kv.api.dto.req.DeleteNoteContentReqDTO;
import com.rednote.kv.api.dto.req.FindNoteContentReqDTO;
import com.rednote.kv.api.dto.rsp.FindNoteContentRspDTO;

/**
 * 笔记内容存储业务
 *
 * @author Jason Feng
 * @since 2026-03-01 13:24
 */
public interface NoteContentService {

    /**
     * 添加笔记内容
     *
     * @param addNoteContentReqDTO
     * @return
     */
    Result<?> addNoteContent(AddNoteContentReqDTO addNoteContentReqDTO);

    /**
     * 查询笔记内容
     *
     * @param findNoteContentReqDTO
     * @return
     */
    Result<FindNoteContentRspDTO> findNoteContent(FindNoteContentReqDTO findNoteContentReqDTO);

    /**
     * 删除笔记内容
     *
     * @param deleteNoteContentReqDTO
     * @return
     */
    Result<?> deleteNoteContent(DeleteNoteContentReqDTO deleteNoteContentReqDTO);
}