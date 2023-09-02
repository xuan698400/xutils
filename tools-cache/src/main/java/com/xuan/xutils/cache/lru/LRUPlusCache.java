package com.xuan.xutils.cache.lru;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 缓存实现，采用LRU算法，能够取得被挤出缓存的对象<br />
 * LRU是Least Recently Used的缩写，即最近最少使用页面置换算法
 *
 * @author xuan
 * @since 2020/10/19
 */
public class LRUPlusCache implements Serializable {
    private static final long serialVersionUID = -1L;

    private final int capacity;
    private final LinkedList<String> list = new LinkedList<>();
    private final HashMap<String, Object> map = new HashMap<>();

    /**
     * 构造方法
     *
     * @param capacity 缓存的容量
     */
    public LRUPlusCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 放到缓存中，会放在缓存中的第一个位置
     *
     * @param key   缓存key
     * @param value 缓存值
     * @return 被挤出的对象，如果为null，说明没有对象被挤出
     */
    public synchronized Object putInCache(String key, Object value) {
        Object oldValue = map.put(key, value);
        if (null != oldValue) {
            return null;
        }

        list.addFirst(key);

        if (list.size() <= capacity) {
            return null;
        }

        String removedKey = list.removeLast();
        return map.remove(removedKey);
    }

    /**
     * 从缓存中读取，被读取的对象会放到缓存中的第一个位置
     *
     * @param key 缓存key
     * @return 缓存值
     */
    public synchronized Object getFromCache(String key) {
        Object value = map.get(key);
        if (null != value) {
            list.remove(key);
            list.addFirst(key);
        }
        return value;
    }

    /**
     * 删除缓存中所有对象
     *
     * @return 被删除的所有对象Map
     */
    public synchronized Map<String, Object> removeAll() {
        HashMap<String, Object> removedMap = new HashMap<>(map);
        list.clear();
        map.clear();
        return removedMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i);
            if (i > 0) {
                sb.append(",");
            }
            sb.append(key);
            sb.append("[");
            sb.append(map.get(key));
            sb.append("]");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRUPlusCache cache = new LRUPlusCache(5);
        for (int i = 0; i < 6; i++) {
            System.out.println(cache.putInCache(i + "", i + "$"));
            System.out.println(cache);
        }
        System.out.println(cache.putInCache("1", "1$"));
        System.out.println(cache);
        System.out.println(cache.getFromCache("1"));
        System.out.println(cache);
        System.out.println(cache.getFromCache("4"));
        System.out.println(cache);
    }

}
