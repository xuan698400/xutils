package com.xuan.distributed.tools.kvmap.core;

/**
 * @author xuan
 * @since 2022/9/21
 */
public class KvMapDaoImpl implements KvMapDao {
    private final static String TABLE_NAME = "dt_lock";

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

}
