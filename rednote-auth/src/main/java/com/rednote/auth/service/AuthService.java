package com.rednote.auth.service;

import com.rednote.auth.model.UpdatePasswordReqVO;
import com.rednote.auth.model.UserLoginReqVO;
import com.rednote.common.response.Result;

/**
 *
 * @author Jason Feng
 * @since 2026-02-01 14:00
 */
public interface AuthService {

    /**
     * 登录与注册
     * @param userLoginReqVO
     * @return
     */
    Result<?> loginAndRegister(UserLoginReqVO userLoginReqVO);

    /**
     * 登出
     * @return
     */
    Result<?> logout();

    /**
     * 修改密码
     * @param updatePasswordReqVO
     * @return
     */
    Result<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO);
}
