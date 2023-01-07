package com.xuan.moho.tools.lock.db;

import javax.sql.DataSource;

import com.xuan.moho.tools.lock.LockException;
import com.xuan.moho.tools.lock.db.DbReentrantLock.Builder;

/**
 * 不可重入锁
 *
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

    public static class Builder {
        private DataSource dataSource;

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public DbNonReentrantLock build() {
            checkParams();
            DbNonReentrantLock dbNonReentrantLock = new DbNonReentrantLock();
            dbNonReentrantLock.dataSource = this.dataSource;
            dbNonReentrantLock.lockDao = new DbLockDao(dataSource);
            dbNonReentrantLock.lockDao.createTable();
            return dbNonReentrantLock;
        }

        private void checkParams() {
            if (null == dataSource) {
                throw new LockException("数据源[dataSource]不能为空");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
