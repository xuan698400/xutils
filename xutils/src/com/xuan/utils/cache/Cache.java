package com.xuan.utils.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 数据缓存接口。
 * 
 * @param <K>
 *            缓存键的类型
 * @param <V>
 *            缓存值的类型
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:37:29 $
 */
public interface Cache<K, V> {

    /**
     * 根据指定的缓存键来获取缓存值。
     * 
     * @param key
     *            缓存键
     * @return 缓存值
     */
    V get(K key);

    /**
     * 将数据新添加到缓存中。
     * 
     * @param key
     *            缓存键
     * @param value
     *            缓存值
     * @return 如果相同 key 的缓存已经存在会返回 <code>false</code>，否则返回 <code>true</code>。
     */
    boolean add(K key, V value);

    /**
     * 将数据新添加到缓存中，并设置过期的时间点。
     * 
     * @param key
     *            缓存键
     * @param value
     *            缓存值
     * @param expiry
     *            缓存过期的时间点
     * @return 如果相同 key 的缓存已经存在会返回 <code>false</code>，否则返回 <code>true</code>。
     */
    boolean add(K key, V value, Date expiry);

    /**
     * 将数据新添加到缓存中，并设置超时时间。
     * 
     * @param key
     *            缓存键
     * @param value
     *            缓存值
     * @param expiry
     *            缓存过期的时长
     * @param unit
     *            时间单位
     * @return 如果相同 key 的缓存已经存在会返回 <code>false</code>，否则返回 <code>true</code>。
     */
    boolean add(K key, V value, long expiry, TimeUnit unit);

    /**
     * 将数据放入缓存。如果相同 key 的缓存已经存在的话，原来的缓存内容将被替换。
     * 
     * @param key
     *            缓存键
     * @param value
     *            缓存值
     */
    void put(K key, V value);

    /**
     * 将数据放入缓存，并设置过期的时间点。如果相同 key 的缓存已经存在的话，原来的缓存内容将被替换。
     * 
     * @param key
     *            缓存键
     * @param value
     *            缓存值
     * @param expiry
     *            缓存过期的时间点
     */
    void put(K key, V value, Date expiry);

    /**
     * 将数据放入缓存，并设置超时时间。如果相同 key 的缓存已经存在的话，原来的缓存内容将被替换。
     * 
     * @param key
     *            缓存键
     * @param value
     *            缓存值
     * @param expiry
     *            缓存过期的时长
     * @param unit
     *            时间单位
     */
    void put(K key, V value, long expiry, TimeUnit unit);

    /**
     * 根据缓存键来删除缓存值。
     * 
     * @param key
     *            缓存键
     */
    void remove(K key);

    /**
     * 对某个计数器进行 <b>加操作</b> 的方法，计数器默认初始化值为 0。
     * 
     * @param key
     *            计数器对应的 key
     * @param delta
     *            变更量
     * @return 加操作之后计数器的值
     */
    long incr(K key, long delta);

    /**
     * 对某个计数器进行 <b>加操作</b> 的方法，计数器默认初始值为 <code>initValue</code>。
     * 
     * @param key
     *            计数器对应的 key
     * @param delta
     *            变更量
     * @param initValue
     *            计数器的初始值
     * @return 加操作之后计数器的值
     */
    long incr(K key, long delta, long initValue);

    /**
     * 对某个计数器进行 <b>减操作</b> 的方法。 如果计数器尚不存在，则返回 0。
     * 
     * @param key
     *            计数器对应的 key
     * @param delta
     *            变更量
     * @return 减操作之后计数器的值
     */
    long decr(K key, long delta);

    /**
     * 对某个计数器进行 <b>减操作</b> 的方法，计数器默认初始值为 <code>initValue</code>。
     * 
     * @param key
     *            计数器对应的 key
     * @param delta
     *            变更量
     * @param initValue
     *            计数器的初始值
     * @return 减操作之后计数器的值
     */
    long decr(K key, long delta, long initValue);

    /**
     * 清除所有缓存。
     */
    void flushAll();

    /**
     * 销毁所有缓存。<br>
     * 调用此方法销毁缓存之后，此缓存对象便无法使用，而调用 <code>flushAll()</code> 方法则没有此限制。
     */
    void destroy();

}
