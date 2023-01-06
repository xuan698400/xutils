package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeTextSpec implements ColumnTypeSpec {

    @Override
    public String buildSqlFragment(SqlSyntax sqlSyntax) {
        return "TEXT";
    }

    public static ColumnTypeTextSpec of() {
        return new ColumnTypeTextSpec();
    }
}
