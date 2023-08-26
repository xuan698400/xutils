package com.xuan.xutils.sql.orm.sqlparams;

import com.xuan.xutils.sql.orm.sqlparams.core.DeleteSQLParamsBuilder;
import com.xuan.xutils.sql.orm.sqlparams.core.InsertSQLParamsBuilder;
import com.xuan.xutils.sql.orm.sqlparams.core.SelectSQLParamsBuilder;
import com.xuan.xutils.sql.orm.sqlparams.core.UpdateSQLParamsBuilder;

/**
 * @author xuan
 * @since 2022/1/25
 */
public class SQLParamsBuilderFactory {

    public static SQLParamsBuilder getInsertSQLParamsBuilder() {
        return new InsertSQLParamsBuilder();
    }

    public static SQLParamsBuilder getUpdateSQLParamsBuilder() {
        return new UpdateSQLParamsBuilder();
    }

    public static SQLParamsBuilder getDeleteSQLParamsBuilder() {
        return new DeleteSQLParamsBuilder();
    }

    public static SQLParamsBuilder getSelectSQLParamsBuilder() {
        return new SelectSQLParamsBuilder();
    }

}
