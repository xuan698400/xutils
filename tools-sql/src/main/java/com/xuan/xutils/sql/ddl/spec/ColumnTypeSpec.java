package com.xuan.xutils.sql.ddl.spec;

import com.xuan.xutils.sql.common.SQLSyntax;

/**
 * @author xuan
 * @since 2023/1/6
 */
public interface ColumnTypeSpec {

    String buildCreateTableColumnTypeSQL(SQLSyntax sqlSyntax);

}
