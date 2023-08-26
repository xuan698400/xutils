package com.xuan.xutils.sql.ddl.spec.columntype;

import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeTinyTextSpec implements ColumnTypeSpec {

    @Override
    public String buildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax) {
        return "TINYTEXT";
    }

    public static ColumnTypeTinyTextSpec of() {
        return new ColumnTypeTinyTextSpec();
    }
}
