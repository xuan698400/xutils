package com.xuan.utils.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的缓存管理器接口实现类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:41:14 $
 */
public class DefaultCacheManager implements CacheManager<Cache<String, Object>> {

    // 缓存管理器是否已经被关闭
    private volatile boolean isShutdown = false;

    // 缓存池
    private final ConcurrentHashMap<String, Cache<String, Object>> cachePool = new ConcurrentHashMap<String, Cache<String, Object>>();

    public DefaultCacheManager() {
    }

    @Override
    public Cache<String, Object> getCache(String cacheName) {
        checkStatus();

        return cachePool.get(cacheName);
    }

    @Override
    public void addCache(String cacheName, Cache<String, Object> cache) {
        checkStatus();

        if (cacheName == null || cacheName.length() == 0) {
            return;
        }

        if (cachePool.get(cacheName) != null) {
            throw new CacheExistsException("Cache " + cacheName + " already exists");
        }

        cachePool.putIfAbsent(cacheName, cache);
    }

    @Override
    public void removeCache(String cacheName) {
        checkStatus();

        if (cacheName == null || cacheName.length() == 0) {
            return;
        }

        Cache<String, Object> cache = cachePool.get(cacheName);
        if (cache == null) {
            return;
        }

        try {
            cache.destroy();
        }
        catch (Exception e) {
            // Ignore
        }
        finally {
            cachePool.remove(cacheName);
        }
    }

    @Override
    public Set<String> getCacheNames() {
        checkStatus();

        return Collections.unmodifiableSet(cachePool.keySet());
    }

    @Override
    public void flush(String cacheName) {
        checkStatus();

        Cache<String, Object> cache = cachePool.get(cacheName);
        if (cache != null) {
            cache.flushAll();
        }
    }

    @Override
    public void flushAll() {
        checkStatus();

        Collection<Cache<String, Object>> caches = cachePool.values();
        for (Cache<String, Object> cache : caches) {
            if (cache != null) {
                cache.flushAll();
            }
        }
    }

    @Override
    public synchronized void shutdown() {
        if (isShutdown) {
            return;
        }

        isShutdown = true;

        try {
            destroyCaches();
        }
        finally {
            cachePool.clear();
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    private void checkStatus() {
        if (isShutdown) {
            throw new IllegalStateException("The " + getClass() + " is shutdown");
        }
    }

    private void destroyCaches() {
        for (Map.Entry<String, Cache<String, Object>> entry : cachePool.entrySet()) {
            Cache<String, Object> cache = entry.getValue();
            if (cache == null) {
                continue;
            }

            try {
                cache.destroy();
            }
            catch (Exception e) {
                // Ignore
            }
        }
    }

}
