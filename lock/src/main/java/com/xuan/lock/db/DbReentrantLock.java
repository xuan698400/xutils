package com.xuan.lock.db;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sql.DataSource;

import com.xuan.lock.common.DbLockModel;

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
            throw new IllegalArgumentException("参数[resource]不能为空");
        }

        String localUniqueValue = buildLocalUniqueValue();

        //1、先查询锁是否被占有
        DbLockModel dbLockModel = DbHelper.selectLock(dataSource, resource);

        //2、锁未被占有，插入记录，尝试获取
        if (null == dbLockModel) {
            return DbHelper.insertLock(dataSource, resource, localUniqueValue, timeoutMilliSecond);
        }

        //3、锁已被占有，判断是否超时，如果超时，进行更新操作，尝试获取
        if (System.currentTimeMillis() > (dbLockModel.getCreateTime() + dbLockModel.getTimeout())) {
            return DbHelper.updateLock(dataSource, resource, localUniqueValue, dbLockModel.getCreateTime(),
                timeoutMilliSecond);
        }

        //4、到这里，表示锁已被占有且没有超时，那么判断持有者是否是当前机器的当前线程（即可重入判定）
        return localUniqueValue.equals(dbLockModel.getValue());
    }

    @Override
    public void unLock(String resource) {
        DbLockModel dbLockModel = DbHelper.selectLock(dataSource, resource);
        String localUniqueValue = buildLocalUniqueValue();

        //只有是自己的锁才去释放，并且要带上时间戳，防止并发时误删别的的锁
        if (null != dbLockModel && localUniqueValue.equals(dbLockModel.getValue())) {
            DbHelper.deleteLock(dataSource, resource, dbLockModel.getCreateTime());
        }
    }

    @Override
    public void forceUnLock(String resource) {
        DbHelper.deleteLock(dataSource, resource);
    }

    private String buildLocalUniqueValue() {
        try {
            return InetAddress.getLocalHost().getHostAddress() + ":" + Thread.currentThread().getName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}

