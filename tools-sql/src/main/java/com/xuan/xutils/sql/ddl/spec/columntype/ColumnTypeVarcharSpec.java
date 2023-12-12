package com.xuan.xutils.sql.ddl.spec.columntype;

import com.xuan.xutils.sql.common.Assert;
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
        Assert.notNull(size, "列类型varchar长度[size]不能为空");
        ColumnTypeVarcharSpec spec = new ColumnTypeVarcharSpec();
        spec.size = size;
        return spec;
    }

}
