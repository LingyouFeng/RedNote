package com.rednote.note.biz.service;


import com.rednote.common.response.Result;
import com.rednote.note.biz.model.vo.FindChannelRspVO;

import java.util.List;

/**
 * 频道业务
 *
 * @author Jason Feng
 * @since 2026-11-21 16:29
 */
public interface ChannelService {

    /**
     * 查询所有频道
     *
     * @return 返回所有频道列表
     */
    Result<List<FindChannelRspVO>> findChannelList();
}
