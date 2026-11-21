package com.rednote.user.biz.rpc;

import com.rednote.common.response.Result;
import com.rednote.count.api.dto.FindUserCountsByIdReqDTO;
import com.rednote.count.api.dto.FindUserCountsByIdRspDTO;
import com.rednote.count.api.fegin.CountFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 计数服务
 *
 * @author Jason Feng
 * @since 2026-04-20 22:23
 */
@Component
public class CountRpcService {

    @Resource
    private CountFeignApi countFeignApi;

    /**
     * 查询用户计数信息
     * @param userId
     * @return
     */
    public FindUserCountsByIdRspDTO findUserCountById(Long userId) {
        FindUserCountsByIdReqDTO findUserCountsByIdReqDTO = new FindUserCountsByIdReqDTO();
        findUserCountsByIdReqDTO.setUserId(userId);

        Result<FindUserCountsByIdRspDTO> response = countFeignApi.findUserCount(findUserCountsByIdReqDTO);

        if (Objects.isNull(response) || !response.isSuccess()) {
            return null;
        }

        return response.getData();
    }

}