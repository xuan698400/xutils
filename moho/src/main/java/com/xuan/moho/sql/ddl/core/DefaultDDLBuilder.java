package com.xuan.moho.sql.ddl.core;

import com.xuan.moho.sql.common.SQLSyntax;
import com.xuan.moho.sql.ddl.DDLBuilder;
import com.xuan.moho.sql.ddl.spec.TableSpec;

/**
 * @author xuan
 * @since 2023/1/7
 */
public class DefaultDDLBuilder implements DDLBuilder {

    @Override
    public String createTableSQL(TableSpec tableSpec, SQLSyntax sqlSyntax) {
        return tableSpec.createTableSQL(sqlSyntax);
    }
}
