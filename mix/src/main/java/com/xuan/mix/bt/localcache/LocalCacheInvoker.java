package com.xuan.mix.bt.localcache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author xuan
 * @since 2021/5/17
 */
public class LocalCacheInvoker {

    public static <R> R call(Class clazz, Object key, Function<Object, R> function) {

        LocalCache localCache = LocalCache.getInstance();
        Object result = localCache.get(clazz, key);
        if (!localCache.isResultCached(result)) {
            R r = function.apply(key);
            localCache.put(clazz, key, r);
            return r;
        }

        if (localCache.isResultNull(result)) {
            return null;
        }

        return (R)result;
    }

    public static <R> Map<Object, R> call(Class clazz, Set<Object> keys,
        Function<Set<Object>, Map<Object, R>> function) {

        LocalCache localCache = LocalCache.getInstance();

        //获取没有缓存的key
        Set<Object> unCachedKeys = localCache.getUnCachedKeys(clazz, keys);

        //回源数据
        Map<Object, R> unCachedDatas = unCachedKeys.isEmpty() ? new HashMap<>() : function.apply(unCachedKeys);

        //补充数据
        Map<Object, R> resultMap = new HashMap<>();
        for (Object key : keys) {
            Object result = localCache.get(clazz, key);
            if (!localCache.isResultCached(result)) {
                R r = unCachedDatas.get(key);
                localCache.put(clazz, key, r);
                resultMap.put(key, r);
            } else {
                resultMap.put(key, LocalCache.OBJ_NULL == result ? null : (R)result);
            }
        }

        return resultMap;
    }

}
