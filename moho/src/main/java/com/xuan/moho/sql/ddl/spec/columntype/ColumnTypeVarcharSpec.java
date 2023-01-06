package com.xuan.moho.sql.ddl.spec.columntype;

import com.xuan.moho.base.exception.Assert;
import com.xuan.moho.base.exception.BizExceptionCodeEnum;
import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeVarcharSpec implements ColumnTypeSpec {

    private Integer size;

    @Override
    public String buildSqlFragment(SqlSyntax sqlSyntax) {
        return String.format("VARCHAR(%s)", size);
    }

    public static ColumnTypeVarcharSpec of(Integer size) {
        Assert.notNull(size, BizExceptionCodeEnum.PARAM_NULL.getCode(), "size is null.");
        ColumnTypeVarcharSpec spec = new ColumnTypeVarcharSpec();
        spec.size = size;
        return spec;
    }

}
