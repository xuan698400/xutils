package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeTinyTextSpec implements ColumnTypeSpec {

    @Override
    public String buildSqlFragment(SqlSyntax sqlSyntax) {
        return "TINYTEXT";
    }

    public static ColumnTypeTinyTextSpec of() {
        return new ColumnTypeTinyTextSpec();
    }
}
