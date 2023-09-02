package com.xuan.xutils.lock.core;

import java.util.Date;

import com.xuan.xutils.lock.DistributedLockException;

/**
 * @author xuan
 * @since 2022/9/9
 */
public interface LockDao {

    void createTable() throws DistributedLockException;

    String selectLockName(String name) throws DistributedLockException;

    void insertIgnore(String name) throws DistributedLockException;

    int updateByHolder(String name, String holder, Date expireTime) throws DistributedLockException;

    int releaseByHolder(String name, String holder) throws DistributedLockException;

    int update(String name, Date expireTime) throws DistributedLockException;

    int release(String name) throws DistributedLockException;
}
