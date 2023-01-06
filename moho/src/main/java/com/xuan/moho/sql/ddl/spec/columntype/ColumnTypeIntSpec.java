package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SQLSyntax;
import com.xuan.moho.sql.ddl.spec.BaseColumnTypeZeroFillSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeIntSpec extends BaseColumnTypeZeroFillSpec {

    @Override
    protected String doBuildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax) {
        return "INT";
    }

    public static ColumnTypeIntSpec of() {
        return new ColumnTypeIntSpec();
    }

}
