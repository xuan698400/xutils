package com.xuan.moho.tools.lock;

/**
 * @author xuan
 * @since 2022/9/9
 */
public interface Lock {

    boolean tryLock(String name, Integer timeoutSecond);

    void unLock(String name);
}
