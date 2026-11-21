package com.rednote.auth.controller;

import com.rednote.auth.service.AuthService;
import com.rednote.auth.model.UpdatePasswordReqVO;
import com.rednote.auth.model.UserLoginReqVO;
import com.rednote.common.response.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jason Feng
 * @since 2026-02-01 14:00
 */
@RestController
public class AuthController {

    @Resource
    private AuthService userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody @Valid UserLoginReqVO userLoginReqVO) {
        return userService.loginAndRegister(userLoginReqVO);
    }

    @PostMapping("/logout")
    public Result<?> logout() {
        return userService.logout();
    }

    @PostMapping("/password/update")
    public Result<?> updatePassword(@RequestBody @Valid UpdatePasswordReqVO updatePasswordReqVO) {
        return userService.updatePassword(updatePasswordReqVO);
    }
}
