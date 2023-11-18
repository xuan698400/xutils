package com.xuan.crudboy.utils;

import java.util.Map;

/**
 * MAP常用工具类
 *
 * @author xuan
 * @since 2022/4/25
 */
public class CbMapUtils {

    /**
     * 判断MAP是否为空
     * MAP为NULL或者集合没有元素都算是空
     *
     * @param map MAP
     * @return true/false
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断MAP是否为非空
     * MAP不为NULL且集合有元素才会判定非空
     *
     * @param map MAP
     * @return true/false
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

}
