package com.xuan.lock;

/**
 * @author xuan
 * @since 2022/1/27
 */
public interface Lock {

    boolean tryLock(String resource, Integer timeoutMilliSecond);

    void unLock(String resource);
}
