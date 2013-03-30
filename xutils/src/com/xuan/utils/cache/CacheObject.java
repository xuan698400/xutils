package com.xuan.utils.cache;

import java.io.Serializable;

/**
 * 用于包装被缓存对象的类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:38:42 $
 */
public class CacheObject implements Serializable {

    private static final long serialVersionUID = -4079226042668764266L;

    private volatile long creationTime;
    private volatile long expiryTime;
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

    public boolean isExpired() {
        long time = creationTime + expiryTime;
        long now = System.currentTimeMillis();
        return time > 0 && time <= now;
    }

}
