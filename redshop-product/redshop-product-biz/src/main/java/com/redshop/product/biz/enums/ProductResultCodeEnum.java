package com.redshop.product.biz.enums;

import com.rednote.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品模块错误码枚举类
 *
 * @author Jason Feng
 * @since 2026-11-26 22:56
 */
@Getter
@AllArgsConstructor
public enum ProductResultCodeEnum implements BaseExceptionInterface {

    // ----------- 业务异常状态码 -----------
    CATEGORY_NOT_EXIST(20001, "商品分类不存在"),
    ;

    private final Integer code;
    private final String message;
}