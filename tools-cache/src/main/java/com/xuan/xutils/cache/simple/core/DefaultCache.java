package com.xuan.xutils.cache.simple.core;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.xuan.xutils.cache.simple.Cache;

/**
 * 默认的简单缓存实现类
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:44:23 $
 */
public class DefaultCache implements Cache<String, Object> {

    /**
     * 内部 map 的个数，根据 key 的 hash 对 moduleSize 取模来定位到具体的某一个内部的 map，减少阻塞情况发生。
     */
    private static final int MODULE_SIZE = 10;

    /**
     * 清理超时缓存的服务运行的时间间隔，默认 10 分钟
     */
    private static final int EXPIRY_INTERVAL = 10;

    /**
     * 具体存放缓存的 map 数组
     */
    private final ConcurrentMap<String, Object>[] cacheMaps;

    /**
     * 存放缓存超时时间的 map
     */
    private final Map<String, Long> cacheTimeMap;

    /**
     * 清理超时缓存的服务
     */
    private final ScheduledExecutorService executorService;

    public DefaultCache() {
        cacheMaps = new ConcurrentMap[MODULE_SIZE];
        for (int i = 0; i < MODULE_SIZE; i++) {
            cacheMaps[i] = new ConcurrentHashMap<>();
        }
        cacheTimeMap = new ConcurrentHashMap<>();

        //定时扫描过期缓存
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new CacheExpiryTask(), 0, EXPIRY_INTERVAL, TimeUnit.MINUTES);

        //System.out.println(CACHE_NAME + " initialized");
    }

    @Override
    public synchronized void destroy() {
        for (Map<String, Object> cacheMap : cacheMaps) {
            cacheMap.clear();
        }
        cacheTimeMap.clear();

        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    @Override
    public Object get(String key) {
        verifyCache(key);
        return getCacheMap(key).get(key);
    }

    @Override
    public boolean add(String key, Object value) {
        verifyCache(key);

        boolean success = false;
        Object previous = getCacheMap(key).putIfAbsent(key, value);
        if (previous == null) {
            //表示新值设置成功
            cacheTimeMap.put(key, -1L);
            success = true;
        }

        return success;
    }

    @Override
    public boolean add(String key, Object value, Date expiry) {
        verifyCache(key);

        boolean success = false;
        Object previous = getCacheMap(key).putIfAbsent(key, value);
        if (previous == null) {
            cacheTimeMap.put(key, expiry.getTime());
            success = true;
        }

        return success;
    }

    @Override
    public boolean add(String key, Object value, long expiry, TimeUnit unit) {
        verifyCache(key);

        boolean success = false;
        Object previous = getCacheMap(key).putIfAbsent(key, value);
        if (previous == null) {
            long _expiry = (expiry == -1L) ? -1L : System.currentTimeMillis() + unit.toMillis(expiry);
            cacheTimeMap.put(key, _expiry);
            success = true;
        }

        return success;
    }

    @Override
    public void put(String key, Object value) {
        if (value == null) {
            return;
        }

        getCacheMap(key).put(key, value);
        cacheTimeMap.put(key, -1L);
    }

    @Override
    public void put(String key, Object value, Date expiry) {
        if (value == null) {
            return;
        }

        getCacheMap(key).put(key, value);
        cacheTimeMap.put(key, expiry.getTime());
    }

    @Override
    public void put(String key, Object value, long expiry, TimeUnit unit) {
        if (value == null) {
            return;
        }

        getCacheMap(key).put(key, value);

        long expiryFix = (expiry == -1L) ? -1L : System.currentTimeMillis() + unit.toMillis(expiry);
        cacheTimeMap.put(key, expiryFix);
    }

    @Override
    public void remove(String key) {
        getCacheMap(key).remove(key);
    }

    @Override
    public long decr(String key, long delta) {
        return decr(key, delta, 0);
    }

    @Override
    public long decr(String key, long delta, long initValue) {
        return incr(key, -delta, initValue);
    }

    @Override
    public long incr(String key, long delta) {
        return incr(key, delta, 0);
    }

    @Override
    public long incr(String key, long delta, long initValue) {
        AtomicLong counter = (AtomicLong)get(key);
        if (counter == null) {
            // 表示计数器还没有存在，创建计数器并尝试添加
            AtomicLong newCounter = new AtomicLong(initValue);
            counter = (AtomicLong)getCacheMap(key).putIfAbsent(key, newCounter);
            if (counter == null) {
                // 表示计数器添加成功，采用新的计数器
                counter = newCounter;
                cacheTimeMap.put(key, -1L);// 将此数据标记为永不过期
            }
        }

        long countValue = counter.addAndGet(delta);
        if (countValue >= 0) {
            return countValue;
        } else {
            counter.compareAndSet(countValue, 0);
            return 0;
        }
    }

    @Override
    public void flushAll() {
        for (Map<String, Object> cacheMap : cacheMaps) {
            cacheMap.clear();
        }
        cacheTimeMap.clear();

        //System.out.println(CACHE_NAME + " flushed");
    }

    public Set<String> keySet() {
        verifyAllCaches();
        return cacheTimeMap.keySet();
    }

    public boolean containsKey(String key) {
        verifyCache(key);
        return getCacheMap(key).containsKey(key);
    }

    public int size() {
        verifyAllCaches();
        return cacheTimeMap.size();
    }

    private ConcurrentMap<String, Object> getCacheMap(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is required");
        }

        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % MODULE_SIZE;
        return cacheMaps[index];
    }

    private void verifyCache(String key) {
        Long expiry = cacheTimeMap.get(key);
        if (isTimeExpiry(expiry)) {
            Map<String, Object> cacheMap = getCacheMap(key);
            cacheMap.remove(key);
            cacheTimeMap.remove(key);

            //System.out.println("Cache(key=" + key + ") is expiry");
        }
    }

    private void verifyAllCaches() {
        Set<String> keys = cacheTimeMap.keySet();
        for (String key : keys) {
            verifyCache(key);
        }
    }

    private boolean isTimeExpiry(Long time) {
        return time != null && time > 0 && time <= System.currentTimeMillis();
    }

    /**
     * 清理获取缓存的任务
     */
    private class CacheExpiryTask implements Runnable {

        @Override
        public void run() {
            verifyAllCaches();
            System.out.println("Cache expiry task executed");
        }
    }

}
