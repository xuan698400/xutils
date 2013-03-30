package com.xuan.utils.cache;

import java.util.Set;

/**
 * 负责对缓存进行管理的接口
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:40:02 $
 */
public interface CacheManager<T extends Cache<?, ?>> {

    /**
     * 
     * 根据缓存名来获取缓存。
     * 
     * @param cacheName
     *            缓存名
     * @return 返回和缓存名称想匹配的缓存，如果没有匹配项则返回 <code>null</code>。
     */
    T getCache(String cacheName);

    /**
     * 添加新的缓存。
     * 
     * @param cacheName
     *            需要添加的缓存名
     * @param cache
     *            需要添加的缓存
     */
    void addCache(String cacheName, T cache);

    /**
     * 删除已经添加的缓存。
     * 
     * @param cacheName
     *            需要删除的缓存名
     */
    void removeCache(String cacheName);

    /**
     * 返回现有缓存名的集合。
     * 
     * @return 缓存名的集合
     */
    Set<String> getCacheNames();

    /**
     * 刷新某个缓存。
     * 
     * @param cacheName
     *            需要刷新的缓存名
     */
    void flush(String cacheName);

    /**
     * 刷新所有缓存。
     */
    void flushAll();

    /**
     * 关闭缓存管理器并对其中的缓存进行销毁处理。
     */
    void shutdown();

    /**
     * 判断缓存管理器是否已经关闭。
     * 
     * @return 如果缓存管理器已经关闭则返回 <code>true</code>，否则返回 <code>false</code>。
     */
    boolean isShutdown();

}
