package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.spec.BaseColumnTypeZeroFillSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeBigIntSpec extends BaseColumnTypeZeroFillSpec {

    @Override
    protected String doBuildSqlFragment(SqlSyntax sqlSyntax) {
        return "BIGINT";
    }

    public static ColumnTypeBigIntSpec of() {
        return new ColumnTypeBigIntSpec();
    }

}
