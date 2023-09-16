package com.xuan.xutils.geek.code.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 缓存实现，采用LRU算法，能够取得被挤出缓存的对象<br />
 * LRU是Least Recently Used的缩写，即最近最少使用页面置换算法
 *
 * @author xuan
 * @since 2020/10/19
 */
public class GkLruCache {

    /**
     * 实现缓存的载体
     */
    private final LinkedHashMap<String, Object> map;

    /**
     * 缓存最大数量
     */
    private int maxSize;

    public GkLruCache(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("maxSize cannot be less than 1");
        }
        this.maxSize = maxSize;
        // 设置accessOrder=true为LRU模式
        this.map = new LinkedHashMap<String, Object>(0, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Entry eldest) {
                return size() > maxSize;
            }
        };
    }

    public synchronized void put(String key, Object value) {
        map.put(key, value);
    }

    public synchronized Object get(String key) {
        return map.get(key);
    }

    public synchronized void remove(String key) {
        map.remove(key);
    }

    public synchronized void removeAll() {
        map.clear();
    }

}
