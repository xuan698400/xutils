package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeDatetimeSpec implements ColumnTypeSpec {

    @Override
    public String buildSqlFragment(SqlSyntax sqlSyntax) {
        return "DATETIME";
    }

    public static ColumnTypeDatetimeSpec of() {
        return new ColumnTypeDatetimeSpec();
    }
}
