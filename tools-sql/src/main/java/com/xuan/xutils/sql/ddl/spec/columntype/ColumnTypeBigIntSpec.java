package com.xuan.xutils.sql.ddl.spec.columntype;

import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.ddl.spec.BaseColumnTypeZeroFillSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeBigIntSpec extends BaseColumnTypeZeroFillSpec {

    @Override
    protected String doBuildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax) {
        return "BIGINT";
    }

    public static ColumnTypeBigIntSpec of() {
        return new ColumnTypeBigIntSpec();
    }

}
