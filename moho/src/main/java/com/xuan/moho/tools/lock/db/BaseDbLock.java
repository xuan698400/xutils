package com.xuan.moho.tools.lock.db;

import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import com.xuan.moho.tools.lock.Lock;
import com.xuan.moho.tools.lock.core.LockDao;

/**
 * @author xuan
 * @since 2022/9/10
 */
public abstract class BaseDbLock implements Lock {

    protected DataSource dataSource;

    protected LockDao lockDao;

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
