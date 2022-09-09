package com.xuan.distributed.tools.lock.core;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xuan
 * @since 2022/9/10
 */
public class DbReentrantLock extends BaseDbLock {

    @Override
    protected boolean doTryLock(String name, Integer timeoutSecond) {
        return lockDao.updateByHolder(name, buildHolder(), buildExpireTime(timeoutSecond)) > 0;
    }

    @Override
    protected void doUnLock(String name) {
        lockDao.releaseByHolder(name, buildHolder());
    }

    private String buildHolder() {
        try {
            return InetAddress.getLocalHost().getHostAddress() + ":" + Thread.currentThread().getName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
