package com.xuan.moho.sql.ddl;

import com.xuan.moho.sql.common.SqlSyntax;

/**
 * @author xuan
 * @since 2023/1/6
 */
public interface DdlBuilder {

    String buildCreateSql(SqlSyntax sqlSyntax);
}
