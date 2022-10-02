package com.xuan.distributed.tools.lock.core;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import com.xuan.distributed.tools.dao.Dao;
import com.xuan.distributed.tools.dao.impl.SimpleDao;
import com.xuan.distributed.tools.lock.LockException;

/**
 * @author xuan
 * @since 2022/9/9
 */
public class LockDaoImpl implements LockDao {

    private Dao dao;

    public LockDaoImpl(DataSource dataSource) {
        dao = new SimpleDao(dataSource);
    }

    private final static String TABLE_NAME = "dt_lock";

    private final static String SELECT_SQL = String.format("SELECT name FROM %s WHERE name=?", TABLE_NAME);

    private final static String SQL_CREATE_TABLE = String.format(""
            + "CREATE TABLE IF NOT EXISTS %s("
            + "id           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',"
            + "name         varchar(128)        NOT NULL                COMMENT '名称',"
            + "holder       varchar(256)        NOT NULL                COMMENT '持有者',"
            + "status       tinyint(4)          NOT NULL                COMMENT '锁状态：0未持有、1持有',"
            + "expire_time  datetime            NOT NULL                COMMENT '过期时间',"
            + "cnt          bigint(20)          NOT NULL                COMMENT '持有次数',"
            + "PRIMARY KEY (`id`),"
            + "UNIQUE `uk_name` (`name`)"
            + ")",
        TABLE_NAME);

    private final static String INSERT_IGNORE_SQL
        = String.format(
        "INSERT IGNORE INTO %s(name, holder, status, expire_time, cnt) VALUES (?, '', 0, now(), 0)", TABLE_NAME);

    private final static String UPDATE_BY_HOLDER_SQL
        = String.format(
        "UPDATE %s SET holder=?, status=1, expire_time=?, cnt=cnt+1 WHERE name=? AND (holder=? OR status=0 OR "
            + "expire_time<now())", TABLE_NAME);

    private final static String RELEASE_BY_HOLDER_SQL
        = String.format(
        "UPDATE %s SET holder='', status=0, expire_time=now() WHERE name=? AND holder=?",
        TABLE_NAME);

    private final static String UPDATE_SQL
        = String.format(
        "UPDATE %s SET status=1, expire_time=?, cnt=cnt+1 WHERE name=? AND (status=0 OR expire_time<now())",
        TABLE_NAME);

    private final static String RELEASE_SQL
        = String.format("UPDATE %s SET status=0, expire_time=now() WHERE name=?", TABLE_NAME);

    @Override
    public void createTable() {
        dao.update(SQL_CREATE_TABLE);
    }

    @Override
    public String selectLockName(String name) throws LockException {
        return dao.query(SELECT_SQL, (rs) -> {
            try {
                if (!rs.next()) {
                    return null;
                }
                return rs.getString(1);
            } catch (SQLException e) {
                throw new LockException(e);
            }
        }, name);
    }

    @Override
    public void insertIgnore(String name) {
        dao.update(INSERT_IGNORE_SQL, name);
    }

    @Override
    public int updateByHolder(String name, String holder, Date expireTime) {
        return dao.update(UPDATE_BY_HOLDER_SQL, holder, expireTime, name, holder);
    }

    @Override
    public int releaseByHolder(String name, String holder) {
        return dao.update(RELEASE_BY_HOLDER_SQL, name, holder);
    }

    @Override
    public int update(String name, Date expireTime) {
        return dao.update(UPDATE_SQL, expireTime, name);
    }

    @Override
    public int release(String name) {
        return dao.update(RELEASE_SQL, name);
    }

}
