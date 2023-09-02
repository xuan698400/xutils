package com.xuan.xutils.cache.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author xuan
 * @since 2021/5/17
 */
public class ThreadLocalCacheInvoker {

    public static <R> R call(String group, String key, Function<String, R> function) {

        ThreadLocalCache localCache = ThreadLocalCache.getInstance();
        Object value = localCache.get(group, key);
        if (!localCache.isValueCached(value)) {
            R newValue = function.apply(key);
            localCache.put(group, key, newValue);
            return newValue;
        }

        if (localCache.isValueNull(value)) {
            return null;
        }

        return (R)value;
    }

    public static <R> Map<Object, R> call(String group, List<String> keyList,
        Function<List<String>, Map<String, R>> function) {

        ThreadLocalCache localCache = ThreadLocalCache.getInstance();

        //1、获取没有缓存过的数据key
        List<String> unCachedKeys = localCache.getUnCachedKeys(group, keyList);

        //2、如果存在没有缓存过的数据，进行数据回源
        Map<String, R> unCachedDatas = null;
        if (!unCachedKeys.isEmpty()) {
            unCachedDatas = function.apply(unCachedKeys);
        }

        //3、从缓存中获取数据，如果没有缓存过，从回源的数据集合里面补充
        Map<Object, R> resultMap = new HashMap<>();
        for (String key : keyList) {
            Object value = localCache.get(group, key);
            if (!localCache.isValueCached(value)) {
                R unCachedValue = null;
                if (null != unCachedDatas) {
                    unCachedValue = unCachedDatas.get(key);
                }
                localCache.put(group, key, unCachedValue);

                if (null != unCachedValue) {
                    resultMap.put(key, unCachedValue);
                }
            } else {
                if (!localCache.isValueNull(value)) {
                    resultMap.put(key, (R)value);
                }
            }
        }

        return resultMap;
    }

}
