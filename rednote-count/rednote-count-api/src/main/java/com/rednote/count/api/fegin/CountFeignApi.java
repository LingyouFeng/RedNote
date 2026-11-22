package com.rednote.count.api.fegin;

import com.rednote.common.response.Result;
import com.rednote.count.api.constant.ApiConstants;
import com.rednote.count.api.dto.FindNoteCountsByIdRspDTO;
import com.rednote.count.api.dto.FindNoteCountsByIdsReqDTO;
import com.rednote.count.api.dto.FindUserCountsByIdReqDTO;
import com.rednote.count.api.dto.FindUserCountsByIdRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 计数服务 Feign 接口
 *
 * @author Jason Feng
 * @since 2026-04-20 21:20
 */
@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface CountFeignApi {

    String PREFIX = "/count";

    /**
     * 查询用户计数
     *
     * @param findUserCountsByIdReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/user/data")
    Result<FindUserCountsByIdRspDTO> findUserCount(@RequestBody FindUserCountsByIdReqDTO findUserCountsByIdReqDTO);

    /**
     * 批量查询笔记计数
     *
     * @param findNoteCountsByIdsReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/notes/data")
    Result<List<FindNoteCountsByIdRspDTO>> findNotesCount(@RequestBody FindNoteCountsByIdsReqDTO findNoteCountsByIdsReqDTO);

}
