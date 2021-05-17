package com.xuan.mix.bt.localcache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xuan
 * @since 2021/5/17
 */
public class LocalCache {

    public static final Object OBJ_NULL = new Object();

    private LocalCache() {
    }

    private static final ThreadLocal<LocalCache> THREAD_LOCAL = new ThreadLocal<LocalCache>() {
        @Override
        protected LocalCache initialValue() {
            return new LocalCache();
        }
    };

    public static LocalCache getInstance() {
        return THREAD_LOCAL.get();
    }

    /**
     * 外部key是Class对象，内部key是缓存对象的key
     */
    private final Map<Class<?>, Map<Object, Object>> cache = new HashMap<>();

    public void clear() {
        cache.clear();
    }

    public void put(Class clazz, Object key, Object value) {
        Map<Object, Object> classCache = getClassCache(clazz);
        if (value != null) {
            classCache.put(key, value);
        } else {
            classCache.put(key, OBJ_NULL);
        }
    }

    public Object get(Class clazz, Object key) {
        Map<Object, Object> classCache = getClassCache(clazz);
        return classCache.get(key);
    }

    public List<Object> batchGet(Class clazz, List<Object> keys) {
        int size = keys.size();
        Object[] array = new Object[size];
        int i = 0;
        for (Object key : keys) {
            array[i++] = get(clazz, key);
        }
        return Arrays.asList(array);
    }

    public Set<Object> getUnCachedKeys(Class clazz, Set<Object> queryKeys) {
        Set<Object> unCachedKeys = new HashSet<>();

        for (Object key : queryKeys) {
            Object result = get(clazz, key);
            if (!isResultCached(result)) {
                unCachedKeys.add(key);
            }
        }
        return unCachedKeys;
    }

    public boolean isResultCached(Object o) {
        return o != null;
    }

    public boolean isResultNull(Object o) {
        return o == OBJ_NULL;
    }

    private Map<Object, Object> getClassCache(Class clazz) {
        return cache.computeIfAbsent(clazz, key -> new HashMap<>());
    }

}
