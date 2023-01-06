package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeLongTextSpec implements ColumnTypeSpec {

    @Override
    public String buildSqlFragment(SqlSyntax sqlSyntax) {
        return "LONGTEXT";
    }

    public static ColumnTypeLongTextSpec of() {
        return new ColumnTypeLongTextSpec();
    }
}
