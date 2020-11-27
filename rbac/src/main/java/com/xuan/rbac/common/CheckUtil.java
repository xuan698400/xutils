package com.xuan.rbac.common;

import java.util.List;

/**
 * @author xuan
 * @since 2020/11/17
 */
public class CheckUtil {

    public static void checkEmpty(String str, Object... args) {
        if (isEmpty(str)) {
            throw new BizException(ErrorCodeEnum.PARAM_EMPTY, args);
        }
    }

    public static void checkEmpty(List list, Object... args) {
        if (isEmpty(list)) {
            throw new BizException(ErrorCodeEnum.PARAM_EMPTY, args);
        }
    }

    public static void checkNull(Object obj, Object... args) {
        if (isNull(obj)) {
            throw new BizException(ErrorCodeEnum.PARAM_NULL, args);
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
        return !CheckUtil.isEmpty(list);
    }

}
