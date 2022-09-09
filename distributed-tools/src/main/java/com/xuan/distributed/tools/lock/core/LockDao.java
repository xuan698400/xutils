package com.xuan.distributed.tools.lock.core;

import java.util.Date;

import com.xuan.distributed.tools.lock.LockException;

/**
 * @author xuan
 * @since 2022/9/9
 */
public interface LockDao {

    void createTable();

    String selectLockName(String name) throws LockException;

    void insertIgnore(String name);

    int updateByHolder(String name, String holder, Date expireTime);

    int releaseByHolder(String name, String holder);

    int update(String name, Date expireTime);

    int release(String name);
}
