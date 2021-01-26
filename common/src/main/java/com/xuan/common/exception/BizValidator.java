package com.xuan.common.exception;

import java.util.List;

/**
 * @author xuan
 * @since 2020/12/3
 */
public class BizValidator {

    public static void checkEmpty(String str, Object... args) {
        if (isEmpty(str)) {
            throw new BizException(BizExceptionCodeEnum.PARAM_EMPTY, args);
        }
    }

    public static void checkEmpty(List list, Object... args) {
        if (isEmpty(list)) {
            throw new BizException(BizExceptionCodeEnum.PARAM_EMPTY, args);
        }
    }

    public static void checkNull(Object obj, Object... args) {
        if (isNull(obj)) {
            throw new BizException(BizExceptionCodeEnum.PARAM_NULL, args);
        }
    }

    public static void checkEmail(String str, Object... args) {
        if (!isEmail(str)) {
            throw new BizException(BizExceptionCodeEnum.PARAM_INVALID, args);
        }
    }

    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isEmpty(List list) {
        return null == list || list.isEmpty();
    }

    public static boolean isNotEmpty(List list) {
        return !BizValidator.isEmpty(list);
    }

    public static boolean isEmail(String str) {
        return str.matches(".+@.+\\.[a-z]+");
    }

}
