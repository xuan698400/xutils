package com.xuan.xutils.sql.ddl.spec.columntype;

import com.xuan.xutils.base.exception.Assert;
import com.xuan.xutils.base.exception.BizExceptionCodeEnum;
import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.ddl.spec.ColumnTypeSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnTypeCharSpec implements ColumnTypeSpec {

    private Integer size;

    @Override
    public String buildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax) {
        return String.format("CHAR(%s)", size);
    }

    public static ColumnTypeCharSpec of(Integer size) {
        Assert.notNull(size, BizExceptionCodeEnum.BIZ_PARAM_EMPTY, "size is null.");
        ColumnTypeCharSpec spec = new ColumnTypeCharSpec();
        spec.size = size;
        return spec;
    }

}
