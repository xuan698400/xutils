package com.xuan.common.utils;

import java.util.List;

/**
 * @author xuan
 * @since 2020/3/14
 */
public class CheckUtil {

    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isEmpty(List list) {
        return null == list || list.isEmpty();
    }

    public static boolean isNull(Object obj) {
        return null == obj;
    }

}
