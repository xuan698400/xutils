package com.xuan.xutils.sql.ddl.core;

import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.ddl.DDLBuilder;
import com.xuan.xutils.sql.ddl.spec.TableSpec;

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
