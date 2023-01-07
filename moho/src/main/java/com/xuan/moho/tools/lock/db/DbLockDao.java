package com.xuan.moho.tools.lock.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.xuan.moho.sql.executer.SQLExecuter;
import com.xuan.moho.sql.executer.core.DefaultSQLExecuter;
import com.xuan.moho.tools.lock.LockException;
import com.xuan.moho.tools.lock.core.LockDao;

/**
 * @author xuan
 * @since 2022/9/9
 */
public class DbLockDao implements LockDao {

    private SQLExecuter sqlExecuter;

    public DbLockDao(DataSource dataSource) {
        sqlExecuter = new DefaultSQLExecuter(dataSource);
    }

    private final static String TABLE_NAME = "moho_lock";

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
        try {
            sqlExecuter.update(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new LockException(e);
        }
    }

    @Override
    public String selectLockName(String name) throws LockException {
        try {
            List<String> nameList = sqlExecuter.query(SELECT_SQL, rs -> rs.getString(1), name);
            if (null != nameList && nameList.size() > 0) {
                return nameList.get(0);
            }
            return null;
        } catch (SQLException e) {
            throw new LockException(e);
        }
    }

    @Override
    public void insertIgnore(String name) {
        try {
            sqlExecuter.update(INSERT_IGNORE_SQL, name);
        } catch (SQLException e) {
            throw new LockException(e);
        }
    }

    @Override
    public int updateByHolder(String name, String holder, Date expireTime) {
        try {
            return sqlExecuter.update(UPDATE_BY_HOLDER_SQL, holder, expireTime, name, holder);
        } catch (SQLException e) {
            throw new LockException(e);
        }
    }

    @Override
    public int releaseByHolder(String name, String holder) {
        try {
            return sqlExecuter.update(RELEASE_BY_HOLDER_SQL, name, holder);
        } catch (SQLException e) {
            throw new LockException(e);
        }
    }

    @Override
    public int update(String name, Date expireTime) {
        try {
            return sqlExecuter.update(UPDATE_SQL, expireTime, name);
        } catch (SQLException e) {
            throw new LockException(e);
        }
    }

    @Override
    public int release(String name) {
        try {
            return sqlExecuter.update(RELEASE_SQL, name);
        } catch (SQLException e) {
            throw new LockException(e);
        }
    }

}
