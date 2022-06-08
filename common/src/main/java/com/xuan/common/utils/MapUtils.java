package com.xuan.common.utils;

import java.util.Map;

/**
 * Map工具类
 *
 * @author xuan
 * @since 2022/4/25
 */
public class MapUtils {

    /**
     * 判空，如果是null或者空集合，都返回true
     *
     * @param map
     * @param <K> K
     * @param <V> V
     * @return true/false
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.isEmpty());
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

}
