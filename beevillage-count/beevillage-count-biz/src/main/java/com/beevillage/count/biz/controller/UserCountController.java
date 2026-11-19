package com.beevillage.count.biz.controller;

import com.beevillage.common.response.Result;
import com.beevillage.count.api.dto.FindUserCountsByIdReqDTO;
import com.beevillage.count.api.dto.FindUserCountsByIdRspDTO;
import com.beevillage.count.biz.service.UserCountService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户维度计数
 *
 * @author Jason Feng
 * @since 2026-04-20 20:41
 */
@RestController
@RequestMapping("/count")
@Slf4j
public class UserCountController {

    @Resource
    private UserCountService userCountService;

    @PostMapping(value = "/user/data")
    public Result<FindUserCountsByIdRspDTO> findUserCountData(@Valid @RequestBody FindUserCountsByIdReqDTO findUserCountsByIdReqDTO) {
        return userCountService.findUserCountData(findUserCountsByIdReqDTO);
    }
}
