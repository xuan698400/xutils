package com.xuan.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class Maps {
    public static <K, V> Map<K, V> newMap() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> newMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> Map<K, V> newMap(K k, V v) {
        Map<K, V> map = new HashMap<>();
        map.put(k, v);
        return map;
    }

}
