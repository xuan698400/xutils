package com.xuan.distributed.tools.lock.core;

import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import com.xuan.distributed.tools.lock.Lock;
import com.xuan.distributed.tools.lock.LockException;

/**
 * @author xuan
 * @since 2022/9/10
 */
public abstract class BaseDbLock implements Lock {

    private DataSource dataSource;

    protected LockDao lockDao;

    public void init() {
        if (null == dataSource) {
            throw new LockException("数据源[dataSource]不能为空");
        }
        lockDao = new LockDaoImpl(dataSource);
        lockDao.createTable();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean tryLock(String name, Integer timeoutSecond) {
        initLock(name);
        return doTryLock(name, timeoutSecond);
    }

    @Override
    public void unLock(String name) {
        initLock(name);
        doUnLock(name);
    }

    protected static Date buildExpireTime(int timeoutSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, timeoutSecond);
        return calendar.getTime();
    }

    private void initLock(String name) {
        String lockName = lockDao.selectLockName(name);
        if (null == lockName) {
            lockDao.insertIgnore(name);
        }
    }

    protected abstract boolean doTryLock(String name, Integer timeoutSecond);

    protected abstract void doUnLock(String name);
}
