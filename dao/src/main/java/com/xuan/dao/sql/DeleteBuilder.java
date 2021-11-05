package com.xuan.dao.sql;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class DeleteBuilder {

    public static DeleteSql build(BaseDO baseDO) {

        Object primaryKeyValue = PrimaryKeyHelper.getAndCheckPrimaryKeyValue(baseDO);

        String sql = "DELETE FROM " + baseDO.tableName() + " WHERE " + baseDO.primaryKey() + "=?";
        Object[] params = new Object[1];
        params[0] = primaryKeyValue;

        DeleteSql deleteSql = new DeleteSql();
        deleteSql.setSql(sql);
        deleteSql.setParams(params);
        return deleteSql;
    }

}
