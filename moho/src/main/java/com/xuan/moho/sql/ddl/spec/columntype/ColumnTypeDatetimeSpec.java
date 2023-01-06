package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SQLSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeDatetimeSpec implements ColumnTypeSpec {

    @Override
    public String buildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax) {
        return "DATETIME";
    }

    public static ColumnTypeDatetimeSpec of() {
        return new ColumnTypeDatetimeSpec();
    }
}
