package com.rednote.note.biz.service;


import com.rednote.common.response.Result;
import com.rednote.note.biz.model.vo.FindTopicListReqVO;
import com.rednote.note.biz.model.vo.FindTopicRspVO;

import java.util.List;

/**
 * 话题业务
 *
 * @author Jason Feng
 * @since 2026-11-21 16:29
 */
public interface TopicService {

    Result<List<FindTopicRspVO>> findTopicList(FindTopicListReqVO findTopicListReqVO);
}
