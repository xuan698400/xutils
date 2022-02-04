package com.xuan.lock;

/**
 * @author xuan
 * @since 2022/1/27
 */
public interface Lock {

    /**
     * 锁住资源resource，同一个时间只能有一个线程操作，可设置超时时间，超过超时时间，锁将会释放
     *
     * @param resource           资源名称
     * @param timeoutMilliSecond 超时时间，单位：毫秒
     * @return 返回取锁是否成功
     */
    boolean tryLock(String resource, Integer timeoutMilliSecond);

    /**
     * 解锁某一个资源
     *
     * @param resource 资源名称
     */
    void unLock(String resource);

    /**
     * 不判断是否是自己的锁，强制解锁，小心使用
     *
     * @param resource 资源名称
     */
    void forceUnLock(String resource);
}
