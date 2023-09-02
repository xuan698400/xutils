package com.xuan.xutils.lock.impl.db;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sql.DataSource;

import com.xuan.xutils.lock.DistributedLockException;

/**
 * 可重入锁
 *
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

    public static class Builder {
        private DataSource dataSource;

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public DbReentrantLock build() {
            checkParams();
            DbReentrantLock dbReentrantLock = new DbReentrantLock();
            dbReentrantLock.dataSource = this.dataSource;
            dbReentrantLock.lockDao = new DbLockDao(dataSource);
            dbReentrantLock.lockDao.createTable();
            return dbReentrantLock;
        }

        private void checkParams() {
            if (null == dataSource) {
                throw new DistributedLockException("数据源[dataSource]不能为空");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
