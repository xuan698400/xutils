package com.xuan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.xuan.dao.model.BaseDO;
import com.xuan.dao.sql.DeleteBuilder;
import com.xuan.dao.sql.DeleteSql;
import com.xuan.dao.sql.InsertSql;
import com.xuan.dao.sql.InsertSqlBuilder;
import com.xuan.dao.sql.SelectSql;
import com.xuan.dao.sql.SelectSqlBuilder;
import com.xuan.dao.sql.UpdateSql;
import com.xuan.dao.sql.UpdateSqlBuilder;
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

    @Override
    public void insert(List<BaseDO> dataList) {
        for (BaseDO baseDO : dataList) {
            InsertSql insertSql = InsertSqlBuilder.build(baseDO);
            jdbcTemplate.update(insertSql.getSql(), insertSql.getParams());
        }
    }

    @Override
    public void update(List<BaseDO> dataList) {
        for (BaseDO baseDO : dataList) {
            UpdateSql updateSql = UpdateSqlBuilder.build(baseDO);
            jdbcTemplate.update(updateSql.getSql(), updateSql.getParams());
        }
    }

    @Override
    public void delete(List<BaseDO> dataList) {
        for (BaseDO baseDO : dataList) {
            DeleteSql deleteSql = DeleteBuilder.build(baseDO);
            jdbcTemplate.update(deleteSql.getSql(), deleteSql.getParams());
        }
    }

    @Override
    public <T extends BaseDO> List<T> select(List<T> conditionList, Class<T> elementType) {
        List<T> dataList = new ArrayList<>();
        for (T condition : conditionList) {
            SelectSql selectSql = SelectSqlBuilder.build(condition);
            List<T> tempList = jdbcTemplate.query(selectSql.getSql(), selectSql.getParams(),
                new BeanPropertyRowMapper<>(elementType));
            if (null != tempList) {
                dataList.addAll(tempList);
            }
        }
        return dataList;
    }

    @Override
    public <T extends BaseDO> List<T> select(String sql, Object[] params, Class<T> elementType) {
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(elementType));
    }

}
