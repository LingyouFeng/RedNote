package com.rednote.note.biz.rpc;

import com.rednote.common.response.Result;
import com.rednote.user.api.dto.req.FindUserByIdReqDTO;
import com.rednote.user.api.dto.resp.FindUserByIdRspDTO;
import com.rednote.user.api.feign.UserFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户服务
 *
 * @author Jason Feng
 * @since 2026-03-06 11:47
 */
@Component
public class UserRpcService {

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public FindUserByIdRspDTO findById(Long userId) {
        FindUserByIdReqDTO findUserByIdReqDTO = new FindUserByIdReqDTO();
        findUserByIdReqDTO.setId(userId);

        Result<FindUserByIdRspDTO> response = userFeignApi.findById(findUserByIdReqDTO);

        if (Objects.isNull(response) || !response.isSuccess()) {
            return null;
        }

        return response.getData();
    }
}
