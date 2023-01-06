package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SQLSyntax;
import com.xuan.moho.sql.ddl.spec.BaseColumnTypeZeroFillSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeTinyIntSpec extends BaseColumnTypeZeroFillSpec {

    @Override
    protected String doBuildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax) {
        return "TINYINT";
    }

    public static ColumnTypeTinyIntSpec of() {
        return new ColumnTypeTinyIntSpec();
    }

}
