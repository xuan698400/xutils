package com.xuan.dao.sql;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class SelectSqlBuilder {

    public static SelectSql build(BaseDO baseDO) {

        Object primaryKeyValue = PrimaryKeyHelper.getAndCheckPrimaryKeyValue(baseDO);

        String sql = "SELECT * FROM " + baseDO.tableName() + " WHERE " + baseDO.primaryKey() + "=?";
        Object[] params = new Object[1];
        params[0] = primaryKeyValue;

        SelectSql selectSql = new SelectSql();
        selectSql.setSql(sql);
        selectSql.setParams(params);
        return selectSql;
    }

}
