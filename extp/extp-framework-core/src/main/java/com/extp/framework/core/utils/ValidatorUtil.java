package com.extp.framework.core.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/8/13
 */
public class ValidatorUtil {

    public static boolean isEmpty(Collection c) {
        return null == c || c.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmpty(Collection c) {
        return !ValidatorUtil.isEmpty(c);
    }
}
