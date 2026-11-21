package com.rednote.kv.biz.service;

import com.rednote.common.response.Result;
import com.rednote.kv.api.dto.req.BatchAddCommentContentReqDTO;
import com.rednote.kv.api.dto.req.BatchFindCommentContentReqDTO;
import com.rednote.kv.api.dto.req.DeleteCommentContentReqDTO;

/**
 * 评论内容存储业务
 *
 * @author Jason Feng
 * @since 2026-03-23 21:23
 */
public interface CommentContentService {

    /**
     * 批量添加评论内容
     * @param batchAddCommentContentReqDTO
     * @return
     */
    Result<?> batchAddCommentContent(BatchAddCommentContentReqDTO batchAddCommentContentReqDTO);

    /**
     * 批量查询评论内容
     * @param batchFindCommentContentReqDTO
     * @return
     */
    Result<?> batchFindCommentContent(BatchFindCommentContentReqDTO batchFindCommentContentReqDTO);

    /**
     * 删除评论内容
     * @param deleteCommentContentReqDTO
     * @return
     */
    Result<?> deleteCommentContent(DeleteCommentContentReqDTO deleteCommentContentReqDTO);
}
