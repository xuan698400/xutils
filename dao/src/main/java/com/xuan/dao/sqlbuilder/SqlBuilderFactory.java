package com.xuan.dao.sqlbuilder;

import com.xuan.dao.common.SqlSyntax;
import com.xuan.dao.sqlbuilder.impl.DeleteSqlBuilder;
import com.xuan.dao.sqlbuilder.impl.InsertSqlBuilder;
import com.xuan.dao.sqlbuilder.impl.SelectSqlBuilder;
import com.xuan.dao.sqlbuilder.impl.UpdateSqlBuilder;

/**
 * @author xuan
 * @since 2022/1/25
 */
public class SqlBuilderFactory {

    public static SqlBuilder getInsertSqlBuilder(SqlSyntax sqlSyntax) {
        return new InsertSqlBuilder();
    }

    public static SqlBuilder getUpdateSqlBuilder(SqlSyntax sqlSyntax) {
        return new UpdateSqlBuilder();
    }

    public static SqlBuilder getDeleteSqlBuilder(SqlSyntax sqlSyntax) {
        return new DeleteSqlBuilder();
    }

    public static SqlBuilder getSelectSqlBuilder(SqlSyntax sqlSyntax) {
        return new SelectSqlBuilder();
    }

}
