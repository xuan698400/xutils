package com.xuan.mix.an.dao.sql;

import com.xuan.mix.an.dao.sql.builder.DeleteSqlBuilder;
import com.xuan.mix.an.dao.sql.builder.InsertSqlBuilder;
import com.xuan.mix.an.dao.sql.builder.SelectSqlBuilder;
import com.xuan.mix.an.dao.sql.builder.UpdateSqlBuilder;

/**
 * @author xuan
 * @since 2022/1/25
 */
public class SqlBuilderFactory {

    public static SqlBuilder getInsertSqlBuilder() {
        return new InsertSqlBuilder();
    }

    public static SqlBuilder getUpdateSqlBuilder() {
        return new UpdateSqlBuilder();
    }

    public static SqlBuilder getDeleteSqlBuilder() {
        return new DeleteSqlBuilder();
    }

    public static SqlBuilder getSelectSqlBuilder() {
        return new SelectSqlBuilder();
    }

}
