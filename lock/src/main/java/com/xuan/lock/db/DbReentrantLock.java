package com.xuan.lock.db;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sql.DataSource;

import com.xuan.lock.DbLock;

/**
 * @author xuan
 * @since 2022/1/27
 */
public class DbReentrantLock implements DbLock {

    private DataSource dataSource;

    @Override
    public void init(DataSource dataSource) {
        if (null == dataSource) {
            throw new IllegalArgumentException("数据库连接池不能空");
        }
        this.dataSource = dataSource;
        DbHelper.createTable(dataSource);
    }

    @Override
    public boolean tryLock(String resource, Integer timeoutMilliSecond) {
        if (null == resource || resource.trim().length() == 0) {
            throw new IllegalArgumentException("参数resource不能为空");
        }

        DbLockModel dbLockModel = DbHelper.selectLock(dataSource, resource);

        //锁不存，尝试新增获取
        String localUniqueValue = buildLocalUniqueValue();
        if (null == dbLockModel) {
            return DbHelper.insertLock(dataSource, resource, localUniqueValue, timeoutMilliSecond);
        }

        //锁是超时状态，尝试更新获取
        if (System.currentTimeMillis() > (dbLockModel.getCreateTime() + dbLockModel.getTimeout())) {
            return DbHelper.updateLock(dataSource, resource, localUniqueValue, dbLockModel.getCreateTime(),
                timeoutMilliSecond);
        }

        //判断锁是否是自己持有
        return localUniqueValue.equals(dbLockModel.getValue());
    }

    @Override
    public void unLock(String resource) {
        DbLockModel dbLockModel = DbHelper.selectLock(dataSource, resource);
        String localUniqueValue = buildLocalUniqueValue();
        if (null != dbLockModel && localUniqueValue.equals(dbLockModel.getValue())) {
            //只有是自己的锁才去释放，并且要带上时间戳，防止并发时误删别的的锁
            DbHelper.deleteLock(dataSource, resource, dbLockModel.getCreateTime());
        }
    }

    private String buildLocalUniqueValue() {
        try {
            return InetAddress.getLocalHost().getHostAddress() + ":" + Thread.currentThread().getName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}

