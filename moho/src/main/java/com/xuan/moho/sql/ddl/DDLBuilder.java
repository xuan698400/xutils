package com.xuan.moho.sql.ddl;

import com.xuan.moho.sql.common.SQLSyntax;

/**
 * @author xuan
 * @since 2023/1/6
 */
public interface DDLBuilder {

    /**
     * 创建表语句
     *
     * @param sqlSyntax 语法
     * @return SQL
     */
    String createTableSQL(SQLSyntax sqlSyntax);
}
