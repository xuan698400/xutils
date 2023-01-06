package com.xuan.moho.sql.ddl.spec;

import com.xuan.moho.sql.common.SQLSyntax;

/**
 * @author xuan
 * @since 2023/1/6
 */
public interface ColumnTypeSpec {

    String buildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax);

}
