package com.xuan.xutils.sql.ddl.spec.columntype;

import com.xuan.xutils.sql.common.Assert;
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
        Assert.notNull(size, "char类型列长度[size]不能为空");
        ColumnTypeCharSpec spec = new ColumnTypeCharSpec();
        spec.size = size;
        return spec;
    }

}
