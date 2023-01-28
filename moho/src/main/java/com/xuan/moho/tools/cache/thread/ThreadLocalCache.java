package com.xuan.moho.tools.cache.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 线程级缓存
 *
 * @author xuan
 * @since 2021/5/17
 */
public class ThreadLocalCache {

    private static final Object OBJ_NULL = new Object();

    private ThreadLocalCache() {
    }

    private static final ThreadLocal<ThreadLocalCache> THREAD_LOCAL = ThreadLocal.withInitial(
        ThreadLocalCache::new);

    public static ThreadLocalCache getInstance() {
        return THREAD_LOCAL.get();
    }

    /**
     * 外层key是需要缓存的数据分组，内部key是缓存数据key
     */
    private final Map<String, Map<String, Object>> cache = new HashMap<>();

    public void clear() {
        cache.clear();
    }

    public void put(String group, String key, Object value) {
        Map<String, Object> classCache = getClassCache(group);
        if (value != null) {
            classCache.put(key, value);
        } else {
            //不存在的缓存使用固定对象占位，可以有效方式缓存穿透
            classCache.put(key, OBJ_NULL);
        }
    }

    public Object getRaw(String group, String key) {
        Object value = get(group, key);
        return isValueNull(value) ? null : value;
    }

    public Object get(String group, String key) {
        Map<String, Object> classCache = getClassCache(group);
        return classCache.get(key);
    }

    public List<Object> batchGet(String group, List<String> keyList) {
        Map<String, Object> classCache = getClassCache(group);
        List<Object> valueList = new ArrayList<>();
        for (String key : keyList) {
            valueList.add(classCache.get(key));
        }
        return valueList;
    }

    public List<String> getUnCachedKeys(String group, List<String> keyList) {
        List<String> unCachedKeyList = new ArrayList<>();

        for (String key : keyList) {
            Object value = get(group, key);
            if (!isValueCached(value)) {
                unCachedKeyList.add(key);
            }
        }
        return unCachedKeyList;
    }

    public boolean isValueCached(Object value) {
        return value != null;
    }

    public boolean isValueNull(Object value) {
        return value == OBJ_NULL;
    }

    private Map<String, Object> getClassCache(String group) {
        return cache.computeIfAbsent(group, key -> new HashMap<>());
    }

}
