package com.xuan.dao.sql;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class SelectSqlBuilder {

    public static SelectSql build(BaseDO baseDO) {
        SelectSql selectSql = new SelectSql();
        return selectSql;
    }

}
