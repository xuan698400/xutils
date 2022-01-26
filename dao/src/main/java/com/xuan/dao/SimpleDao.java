package com.xuan.dao;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.dao.common.DataModel;
import com.xuan.dao.common.SqlSyntax;
import com.xuan.dao.sqlbuilder.SqlBuilder;
import com.xuan.dao.sqlbuilder.SqlBuilderFactory;
import com.xuan.dao.sqlbuilder.SqlModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/11/5
 */
@Component
public class SimpleDao implements Dao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private SqlBuilder insertSqlBuilder = SqlBuilderFactory.getInsertSqlBuilder(SqlSyntax.MYSQL);
    private SqlBuilder updateSqlBuilder = SqlBuilderFactory.getUpdateSqlBuilder(SqlSyntax.MYSQL);
    private SqlBuilder deleteSqlBuilder = SqlBuilderFactory.getDeleteSqlBuilder(SqlSyntax.MYSQL);
    private SqlBuilder selectSqlBuilder = SqlBuilderFactory.getDeleteSqlBuilder(SqlSyntax.MYSQL);

    @Override
    public int insert(DataModel dataModel) {
        SqlModel sqlModel = insertSqlBuilder.getSql(dataModel);
        return jdbcTemplate.update(sqlModel.getSql(), sqlModel.getParams().toArray(new Object[0]));
    }

    @Override
    public int update(DataModel dataModel) {
        SqlModel sqlModel = updateSqlBuilder.getSql(dataModel);
        return jdbcTemplate.update(sqlModel.getSql(), sqlModel.getParams().toArray(new Object[0]));
    }

    @Override
    public int delete(DataModel dataModel) {
        SqlModel sqlModel = deleteSqlBuilder.getSql(dataModel);
        return jdbcTemplate.update(sqlModel.getSql(), sqlModel.getParams().toArray(new Object[0]));
    }

    @Override
    public <T extends DataModel> List<T> select(DataModel dataModel, Class<T> elementType) {
        SqlModel sqlModel = selectSqlBuilder.getSql(dataModel);
        return jdbcTemplate.query(sqlModel.getSql(), sqlModel.getParams().toArray(new Object[0]),
            new BeanPropertyRowMapper<>(elementType));
    }

}
