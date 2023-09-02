package com.xuan.xutils.lock;

/**
 * 分布式锁接口
 *
 * @author xuan
 * @since 2022/9/9
 */
public interface DistributedLock {

    /**
     * 加锁，尝试去锁名称为name的锁，并设置拥有锁的超时时间
     *
     * @param name          锁name
     * @param timeoutSecond 拥有锁的超时时间，单位：秒
     * @return 返回是否成功
     */
    boolean tryLock(String name, Integer timeoutSecond);

    /**
     * 解锁
     *
     * @param name 锁name
     */
    void unLock(String name);
}
