package com.xuan.moho.sql.ddl;

import com.xuan.moho.sql.common.SQLSyntax;
import com.xuan.moho.sql.ddl.spec.TableSpec;

/**
 * @author xuan
 * @since 2023/1/6
 */
public interface DDLBuilder {

    /**
     * 创建表语句
     *
     * @param tableSpec 表元数据
     * @param sqlSyntax 语法
     * @return SQL
     */
    String createTableSQL(TableSpec tableSpec, SQLSyntax sqlSyntax);
}
