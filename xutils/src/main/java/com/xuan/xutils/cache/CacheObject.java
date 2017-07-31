package com.xuan.xutils.cache;

import java.io.Serializable;

/**
 * 用于包装被缓存对象的类
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
     * 缓存获取时间,单位:毫秒
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
     * @return
     */
    public boolean isExpired() {
        long time = creationTime + expiryTime;
        long now = System.currentTimeMillis();
        return time > 0 && time <= now;
    }

}
