package com.xuan.moho.base.utils;

import java.util.Map;

/**
 * @author xuan
 * @since 2022/4/25
 */
public class MapUtils {

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.isEmpty());
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

}
