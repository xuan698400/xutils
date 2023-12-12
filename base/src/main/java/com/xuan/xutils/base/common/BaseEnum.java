package com.xuan.xutils.base.common;

/**
 * @author xuan
 * @since 2023/11/30
 */
public interface BaseEnum {

    String getCode();

    String getDesc();

    static <T extends BaseEnum> T codeOf(Class<T> clazz, String code) {
        if (null == code) {
            return null;
        }
        for (T e : clazz.getEnumConstants()) {
            if (code.equals(e.getCode())) {
                return e;
            }
        }
        return null;
    }

    static <T extends BaseEnum> boolean isContain(Class<T> clazz, String code) {
        if (null == code || code.trim().length() == 0) {
            return false;
        }

        for (T e : clazz.getEnumConstants()) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    default boolean eq(String code) {
        if (null == code || code.trim().length() == 0) {
            return false;
        }
        return code.equals(getCode());
    }

    default boolean isEq(BaseEnum baseEnum) {
        if (null == baseEnum) {
            return false;
        }
        return baseEnum.getCode().equals(getCode());
    }

}
