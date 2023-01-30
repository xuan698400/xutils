package com.xuan.moho.design.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**

 * @author xuan
 * @since 2023/1/29
 */
public class SingletonPool {
    private static final Map<String, Object> POOL = new ConcurrentHashMap<>();

    private SingletonPool() {}

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        String key = clazz.getName();
        T obj = (T)POOL.get(key);
        if (null == obj) {
            synchronized (SingletonPool.class) {
                obj = (T)POOL.get(key);
                if (null == obj) {
                    POOL.put(key, clazz.newInstance());
                }
            }
        }
        return obj;
    }

    public static void remove(Class clazz) {
        if (null != clazz) {
            String key = clazz.getName();
            POOL.remove(key);
        }
    }

    public static void removeAll() {
        POOL.clear();
    }
}
