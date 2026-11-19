package com.beevillage.user.biz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取用户主页信息
 *
 * @author Jason Feng
 * @since 2026-04-20 20:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserProfileReqVO {

    /**
     * 用户 ID
     */
    private Long userId;

}
