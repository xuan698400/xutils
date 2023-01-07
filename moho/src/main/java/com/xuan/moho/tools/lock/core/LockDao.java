package com.xuan.moho.tools.lock.core;

import java.util.Date;

import com.xuan.moho.tools.lock.LockException;

/**
 * @author xuan
 * @since 2022/9/9
 */
public interface LockDao {

    void createTable() throws LockException;

    String selectLockName(String name) throws LockException;

    void insertIgnore(String name) throws LockException;

    int updateByHolder(String name, String holder, Date expireTime) throws LockException;

    int releaseByHolder(String name, String holder) throws LockException;

    int update(String name, Date expireTime) throws LockException;

    int release(String name) throws LockException;
}
