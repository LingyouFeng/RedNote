package com.rednote.auth.service;

import com.rednote.auth.model.SendVerificationCodeVO;
import com.rednote.common.response.Result;

/**
 * 发送验证码服务
 *
 * @author Jason Feng
 * @since 2026-02-01 11:25
 */
public interface VerificationCodeService {

    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeVO   用户手机号
     * @return                          请求响应结果
     */
    Result<?> send(SendVerificationCodeVO sendVerificationCodeVO);
}
