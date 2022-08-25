package com.xuan.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.xuan.common.exception.Assert;
import com.xuan.common.exception.BizException;
import com.xuan.common.model.page.PageData;
import com.xuan.common.model.page.PageQuery;
import com.xuan.common.utils.DateUtils;
import com.xuan.dao.common.DataModel;
import com.xuan.dao.common.SqlSyntax;
import com.xuan.dao.sqlbuilder.SqlBuilder;
import com.xuan.dao.sqlbuilder.SqlBuilderFactory;
import com.xuan.dao.sqlbuilder.SqlCreator;
import com.xuan.dao.sqlbuilder.SqlModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class SimpleDao implements Dao {

    private JdbcTemplate jdbcTemplate;

    public SimpleDao(DataSource dataSource) {
        Assert.notNull(dataSource, "PARAM_INVALID", "数据源不能为空，请先配置数据源。");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private SqlBuilder insertSqlBuilder = SqlBuilderFactory.getInsertSqlBuilder(SqlSyntax.MYSQL);
    private SqlBuilder updateSqlBuilder = SqlBuilderFactory.getUpdateSqlBuilder(SqlSyntax.MYSQL);
    private SqlBuilder deleteSqlBuilder = SqlBuilderFactory.getDeleteSqlBuilder(SqlSyntax.MYSQL);
    private SqlBuilder selectSqlBuilder = SqlBuilderFactory.getSelectSqlBuilder(SqlSyntax.MYSQL);

    @Override
    public long insertBackId(DataModel dataModel) {
        SqlModel sqlModel = insertSqlBuilder.getSql(dataModel);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((conn) -> {
            PreparedStatement ps = conn.prepareStatement(sqlModel.getSql(), Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Object obj : sqlModel.getParams()) {
                if (obj instanceof Short) {
                    ps.setShort(i++, (Short)obj);
                } else if (obj instanceof Integer) {
                    ps.setInt(i++, (Integer)obj);
                } else if (obj instanceof Long) {
                    ps.setLong(i++, (Long)obj);
                } else if (obj instanceof Float) {
                    ps.setFloat(i++, (Float)obj);
                } else if (obj instanceof Double) {
                    ps.setDouble(i++, (Double)obj);
                } else if (obj instanceof String) {
                    ps.setString(i++, (String)obj);
                } else if (obj instanceof Date) {
                    ps.setString(i++, DateUtils.date2String((Date)obj));
                } else {
                    throw new BizException("DB_ERROR", "SimpleDao_insertBackId. 原因：不能识别字段类型。类型：" + obj.getClass());
                }
            }
            return ps;
        }, keyHolder);

        if (null == keyHolder.getKey()) {
            throw new BizException("DB_ERROR", "SimpleDao_insertBackId. 原因：未能返回新ID");
        }

        return keyHolder.getKey().longValue();
    }

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
    public int update(SqlCreator sqlCreator) {
        return jdbcTemplate.update(sqlCreator.getSql(), sqlCreator.getArgs());
    }

    @Override
    public <T extends DataModel> List<T> select(DataModel dataModel, Class<T> elementType) {
        SqlModel sqlModel = selectSqlBuilder.getSql(dataModel);
        return jdbcTemplate.query(sqlModel.getSql(), sqlModel.getParams().toArray(new Object[0]),
            new BeanPropertyRowMapper<>(elementType));
    }

    @Override
    public <T extends DataModel> List<T> select(SqlCreator creator, Class<T> elementType) {
        return jdbcTemplate.query(creator.getSql(), creator.getArgs(), new BeanPropertyRowMapper<>(elementType));
    }

    @Override
    public Long count(SqlCreator creator) {
        return jdbcTemplate.queryForObject(creator.getSql(), creator.getArgs(), Long.class);
    }

    @Override
    public <T extends DataModel> PageData<T> selectPage(SqlCreator creator, PageQuery pageQuery, Class<T> elementType) {

        Long totalCount = null;
        if (null != pageQuery && pageQuery.isNeedTotalCount()) {
            totalCount = jdbcTemplate.queryForObject(creator.getSql().replace("*",
                "count(1)"), creator.getArgs(), Long.class);
        }

        creator.page(pageQuery, null != pageQuery);
        List<T> dataList = select(creator, elementType);

        return PageData.of(dataList, totalCount);
    }

}
