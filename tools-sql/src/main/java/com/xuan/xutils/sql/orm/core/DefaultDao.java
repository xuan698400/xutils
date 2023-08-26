package com.xuan.xutils.sql.orm.core;

import java.sql.SQLException;
import java.util.List;

import com.xuan.xutils.base.model.page.PageData;
import com.xuan.xutils.base.model.page.PageQuery;
import com.xuan.xutils.sql.executer.SQLExecuter;
import com.xuan.xutils.sql.orm.Dao;
import com.xuan.xutils.sql.orm.model.DataModel;
import com.xuan.xutils.sql.orm.resultsetmapping.BeanResultSetMapping;
import com.xuan.xutils.sql.orm.resultsetmapping.LongResultSetMapping;
import com.xuan.xutils.sql.orm.sqlparams.SQLParams;
import com.xuan.xutils.sql.orm.sqlparams.SQLParamsBuilder;
import com.xuan.xutils.sql.orm.sqlparams.SQLParamsBuilderFactory;
import com.xuan.xutils.sql.orm.sqlparams.SQLParamsQueryCreator;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class DefaultDao implements Dao {

    private final static SQLParamsBuilder INSERT_SQL_PARAMS_BUILDER = SQLParamsBuilderFactory
        .getInsertSQLParamsBuilder();
    private final static SQLParamsBuilder UPDATE_SQL_PARAMS_BUILDER = SQLParamsBuilderFactory
        .getUpdateSQLParamsBuilder();
    private final static SQLParamsBuilder DELETE_SQL_PARAMS_BUILDER = SQLParamsBuilderFactory
        .getDeleteSQLParamsBuilder();
    private final static SQLParamsBuilder SELECT_SQL_PARAMS_BUILDER = SQLParamsBuilderFactory
        .getSelectSQLParamsBuilder();

    private SQLExecuter sqlExecuter;

    public DefaultDao(SQLExecuter sqlExecuter) {
        this.sqlExecuter = sqlExecuter;
    }

    @Override
    public long insertBackId(DataModel dataModel) throws SQLException {
        SQLParams sqlParams = INSERT_SQL_PARAMS_BUILDER.getSQLParams(dataModel);
        return sqlExecuter.insertBackId(sqlParams.getSql(), sqlParams.buildParamsArray());
    }

    @Override
    public int insert(DataModel dataModel) throws SQLException {
        SQLParams sqlParams = INSERT_SQL_PARAMS_BUILDER.getSQLParams(dataModel);
        return sqlExecuter.update(sqlParams.getSql(), sqlParams.buildParamsArray());
    }

    @Override
    public int update(DataModel dataModel) throws SQLException {
        SQLParams sqlParams = UPDATE_SQL_PARAMS_BUILDER.getSQLParams(dataModel);
        return sqlExecuter.update(sqlParams.getSql(), sqlParams.buildParamsArray());
    }

    @Override
    public int delete(DataModel dataModel) throws SQLException {
        SQLParams sqlParams = DELETE_SQL_PARAMS_BUILDER.getSQLParams(dataModel);
        return sqlExecuter.update(sqlParams.getSql(), sqlParams.buildParamsArray());
    }

    @Override
    public int update(SQLParamsQueryCreator sqlCreator) throws SQLException {
        return sqlExecuter.update(sqlCreator.getSql(), sqlCreator.getArgs());
    }

    @Override
    public <T extends DataModel> List<T> select(DataModel dataModel, Class<T> elementType) throws SQLException {
        SQLParams sqlParams = SELECT_SQL_PARAMS_BUILDER.getSQLParams(dataModel);
        return sqlExecuter.queryList(sqlParams.getSql(), new BeanResultSetMapping<>(elementType),
            sqlParams.buildParamsArray());
    }

    @Override
    public <T extends DataModel> List<T> select(SQLParamsQueryCreator creator, Class<T> elementType)
        throws SQLException {
        return sqlExecuter.queryList(creator.getSql(), new BeanResultSetMapping<>(elementType), creator.getArgs());
    }

    @Override
    public Long count(SQLParamsQueryCreator sqlCreator) throws SQLException {
        return sqlExecuter.queryObject(toCountSql(sqlCreator.getSql()), new LongResultSetMapping(),
            sqlCreator.getArgs());
    }

    @Override
    public <T extends DataModel> PageData<T> selectPage(SQLParamsQueryCreator creator, PageQuery pageQuery,
        Class<T> elementType) throws SQLException {

        Long totalCount = null;
        if (null != pageQuery && pageQuery.isNeedTotalCount()) {
            totalCount = count(creator);
        }

        creator.page(pageQuery, null != pageQuery);
        List<T> dataList = select(creator, elementType);

        return PageData.of(dataList, totalCount);
    }

    private static String toCountSql(String sql) {
        if (sql.endsWith(";")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        sql = sql.replaceAll("\n", " ");
        return "SELECT count(1) as total FROM (" + sql + ") AS count_temp";
    }

}
