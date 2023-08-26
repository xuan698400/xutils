package com.xuan.xutils.sql.ddl.spec.columntype;

import com.xuan.xutils.base.exception.Assert;
import com.xuan.xutils.base.exception.BizExceptionCodeEnum;
import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeVarcharSpec implements ColumnTypeSpec {

    private Integer size;

    @Override
    public String buildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax) {
        return String.format("VARCHAR(%s)", size);
    }

    public static ColumnTypeVarcharSpec of(Integer size) {
        Assert.notNull(size, BizExceptionCodeEnum.BIZ_PARAM_NULL, "size is null.");
        ColumnTypeVarcharSpec spec = new ColumnTypeVarcharSpec();
        spec.size = size;
        return spec;
    }

}
