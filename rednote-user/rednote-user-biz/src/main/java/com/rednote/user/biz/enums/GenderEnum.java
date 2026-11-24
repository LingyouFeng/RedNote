package com.rednote.user.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 性别枚举类
 *
 * @author Jason Feng
 * @since 2026-02-21 16:54
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    UNKNOWN(0),
    FEMALE(1),
    MALE(2);

    private final Integer value;

    public static boolean isValid(Integer value) {
        for (GenderEnum loginTypeEnum : GenderEnum.values()) {
            if (Objects.equals(value, loginTypeEnum.getValue())) {
                return true;
            }
        }
        return false;
    }

}

