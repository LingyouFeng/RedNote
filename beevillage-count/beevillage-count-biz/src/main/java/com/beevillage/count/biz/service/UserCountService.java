package com.beevillage.count.biz.service;


import com.beevillage.common.response.Result;
import com.beevillage.count.api.dto.FindUserCountsByIdReqDTO;
import com.beevillage.count.api.dto.FindUserCountsByIdRspDTO;

/**
 * 用户计数业务
 *
 * @author Jason Feng
 * @since 2026-04-19 17:07
 */
public interface UserCountService {

    /**
     * 查询用户相关计数
     * @param findUserCountsByIdReqDTO
     * @return
     */
    Result<FindUserCountsByIdRspDTO> findUserCountData(FindUserCountsByIdReqDTO findUserCountsByIdReqDTO);
}
