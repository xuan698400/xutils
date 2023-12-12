package com.xuan.demo.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟缓存读/写
 *
 * @author xuan
 * @since 2023/12/11
 */
public class MockCacheHelper {

    private final static Map<String, String> CACHE_DATA = new HashMap<>();

    public static String get(String key) {
        return CACHE_DATA.get(key);
    }

    public static String put(String key, String value) {
        return CACHE_DATA.put(key, value);
    }

}
