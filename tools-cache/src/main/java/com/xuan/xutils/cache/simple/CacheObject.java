package com.xuan.xutils.cache.simple;

import java.io.Serializable;

/**
 * 用于包装被缓存对象的类
 * 有时候，业务想自己控制缓存超时的操作，那么可以用户这个对象包裹实际要缓存的对象，然后设置超时时间为永久，这样
 * 业务就可以100%取到缓存的对象，并且自己控制判断，该对象是否超时
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:38:42 $
 */
public class CacheObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时的时间戳,单位:毫秒
     */
    private volatile long creationTime;

    /**
     * 缓存过期时间,单位:毫秒
     */
    private volatile long expiryTime;

    /**
     * 缓存对象
     */
    private Object value;

    public CacheObject() {
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 判断缓存是否过期
     *
     * @return true/false
     */
    public boolean isExpired() {
        long time = creationTime + expiryTime;
        long now = System.currentTimeMillis();
        return time > 0 && time <= now;
    }

}
