package com.xuan.distributed.tools.lock.core;

/**
 * @author xuan
 * @since 2022/9/10
 */
public class DbNonReentrantLock extends BaseDbLock {
    @Override
    protected boolean doTryLock(String name, Integer timeoutSecond) {
        return lockDao.update(name, buildExpireTime(timeoutSecond)) > 0;
    }

    @Override
    protected void doUnLock(String name) {
        lockDao.release(name);
    }

}
