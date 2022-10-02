package com.xuan.distributed.tools.sequence.core;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.xuan.distributed.tools.dao.Dao;
import com.xuan.distributed.tools.dao.impl.SimpleDao;
import com.xuan.distributed.tools.sequence.SequenceException;

/**
 * @author xuan
 * @date 2018/4/29
 */
public class SequenceRangeDaoImpl implements SequenceRangeDao {

    private Dao dao;

    public SequenceRangeDaoImpl(DataSource dataSource) {
        dao = new SimpleDao(dataSource);
    }

    private final static String SEQUENCE_TABLE_NAME = "dt_sequence_range";

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
        dao.update(SQL_CREATE_TABLE);
    }

    @Override
    public void initRange(String name, long value) throws SequenceException {
        dao.update(SQL_INSERT_RANGE, name, value);
    }

    @Override
    public boolean updateRange(String name, Long newValue, Long oldValue) throws SequenceException {
        return dao.update(SQL_UPDATE_RANGE, newValue, name, oldValue) > 0;
    }

    @Override
    public Long selectRange(String name) throws SequenceException {
        return dao.query(SQL_SELECT_RANGE, (rs) -> {
            try {
                if (!rs.next()) {
                    //第一次时没有初始化记录，外部调用时有重试机制
                    return null;
                }
                return rs.getLong(1);
            } catch (SQLException e) {
                throw new SequenceException(e);
            }
        }, name);
    }

}
