package com.xuan.utils.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 缓存实现，采用LRU算法，能够取得被挤出缓存的对象<br />
 * LRU是Least Recently Used的缩写，即最近最少使用页面置换算法
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:42:57 $
 */
public class LRUPlusCache implements Serializable {

    private static final long serialVersionUID = -4174256121921240092L;

    private final int capacity;
    private final LinkedList<String> list = new LinkedList<String>();
    private final HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * 构造方法
     * 
     * @param capacity
     *            缓存的容量
     */
    public LRUPlusCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 放到缓存中，会放在缓存中的第一个位置
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @return 被挤出的对象，如果为null，说明没有对象被挤出
     */
    public synchronized Object putInCache(String key, Object value) {
        Object oldValue = map.put(key, value);
        if (oldValue != null) {
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
     * @param key
     * @return 对象
     */
    public synchronized Object getFromCache(String key) {
        Object value = map.get(key);
        if (value != null) {
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
        HashMap<String, Object> removedMap = new HashMap<String, Object>(map);
        list.clear();
        map.clear();
        return removedMap;
    }

    @Override
    public String toString() {
        StringBuilder StringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i);
            if (i > 0) {
                StringBuilder.append(",");
            }
            StringBuilder.append(key);
            StringBuilder.append("[");
            StringBuilder.append(map.get(key));
            StringBuilder.append("]");
        }
        return StringBuilder.toString();
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
