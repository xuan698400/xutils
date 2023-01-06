package com.xuan.moho.sql.ddl.spec;

import com.xuan.moho.sql.common.SqlSyntax;

/**
 * @author xuan
 * @since 2023/1/6
 */
public interface ColumnTypeSpec {

    String buildSqlFragment(SqlSyntax sqlSyntax);

}