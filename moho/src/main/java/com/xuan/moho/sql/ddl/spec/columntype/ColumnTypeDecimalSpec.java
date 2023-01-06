package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeDecimalSpec implements ColumnTypeSpec {

    /**
     * 精度位数，例如：999.1111，m=7
     */
    private Integer m;
    /**
     * 小数点后位数，例如：999.1111，d=4
     */
    private Integer d;

    @Override
    public String buildSqlFragment(SqlSyntax sqlSyntax) {
        return String.format("DECIMAL(%s,%s)", m, d);
    }

    public static ColumnTypeDecimalSpec of(Integer m, Integer d) {
        ColumnTypeDecimalSpec spec = new ColumnTypeDecimalSpec();
        spec.m = m;
        spec.d = d;
        return spec;
    }

}
