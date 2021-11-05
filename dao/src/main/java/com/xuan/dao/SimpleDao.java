package com.xuan.dao;

import javax.annotation.Resource;

import com.xuan.dao.model.BaseDO;
import com.xuan.dao.utils.InsertSql;
import com.xuan.dao.utils.InsertSqlBuilder;
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
    public int insert(BaseDO baseDO) {

        InsertSql insertSql = InsertSqlBuilder.build(baseDO);

        return jdbcTemplate.update(insertSql.getSql(), insertSql.getParams());
    }

}
