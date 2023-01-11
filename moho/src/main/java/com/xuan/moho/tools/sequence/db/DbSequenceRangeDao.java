package com.xuan.moho.tools.sequence.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.xuan.moho.sql.executer.SQLExecuter;
import com.xuan.moho.sql.executer.core.DefaultSQLExecuter;
import com.xuan.moho.sql.orm.resultsetmapping.LongResultSetMapping;
import com.xuan.moho.tools.sequence.SequenceException;
import com.xuan.moho.tools.sequence.core.SequenceRangeDao;

/**
 * @author xuan
 * @date 2018/4/29
 */
public class DbSequenceRangeDao implements SequenceRangeDao {

    private SQLExecuter sqlExecuter;

    public DbSequenceRangeDao(DataSource dataSource) {
        sqlExecuter = new DefaultSQLExecuter(dataSource);
    }

    private final static String SEQUENCE_TABLE_NAME = "moho_sequence_range";

    private final static String SQL_CREATE_TABLE = String.format(""
            + "CREATE TABLE IF NOT EXISTS %s("
            + "id           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',"
            + "name         varchar(128)         NOT NULL                COMMENT '区间名',"
            + "value        bigint(20)          NOT NULL                COMMENT '区间值',"
            + "modify_time  datetime            NOT NULL                COMMENT '最新修改值',"
            + "PRIMARY KEY (`id`),"
            + "UNIQUE `uk_name` (`name`)"
            + ")",
        SEQUENCE_TABLE_NAME);

    private final static String SQL_INSERT_RANGE =
        String.format("INSERT IGNORE INTO %s(name,value,modify_time) VALUE(?,?,now())",
            SEQUENCE_TABLE_NAME);

    private final static String SQL_UPDATE_RANGE =
        String.format("UPDATE %s SET value=?,modify_time=now() WHERE name=? AND value=?",
            SEQUENCE_TABLE_NAME);

    private final static String SQL_SELECT_RANGE =
        String.format("SELECT value FROM %s WHERE name=?", SEQUENCE_TABLE_NAME);

    @Override
    public void createRangeTable() throws SequenceException {
        try {
            sqlExecuter.update(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new SequenceException(e);
        }
    }

    @Override
    public void initRange(String name, long value) throws SequenceException {
        try {
            sqlExecuter.update(SQL_INSERT_RANGE, name, value);
        } catch (SQLException e) {
            throw new SequenceException(e);
        }
    }

    @Override
    public boolean updateRange(String name, Long newValue, Long oldValue) throws SequenceException {
        try {
            return sqlExecuter.update(SQL_UPDATE_RANGE, newValue, name, oldValue) > 0;
        } catch (SQLException e) {
            throw new SequenceException(e);
        }
    }

    @Override
    public Long selectRange(String name) throws SequenceException {
        try {
            return sqlExecuter.queryObject(SQL_SELECT_RANGE, new LongResultSetMapping(), name);
        } catch (SQLException e) {
            throw new SequenceException(e);
        }
    }

}
