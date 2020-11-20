package com.xuan.user.common;

import java.util.List;

/**
 * @author xuan
 * @since 2020/11/17
 */
public class UserCheckUtil {

    public static void checkEmpty(String str, Object... args) {
        if (isEmpty(str)) {
            throw new UserException(UserExceptionCodeEnum.PARAM_EMPTY, args);
        }
    }

    public static void checkEmpty(List list, Object... args) {
        if (isEmpty(list)) {
            throw new UserException(UserExceptionCodeEnum.PARAM_EMPTY, args);
        }
    }

    public static void checkNull(Object obj, Object... args) {
        if (isNull(obj)) {
            throw new UserException(UserExceptionCodeEnum.PARAM_NULL, args);
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
        return !UserCheckUtil.isEmpty(list);
    }

}
