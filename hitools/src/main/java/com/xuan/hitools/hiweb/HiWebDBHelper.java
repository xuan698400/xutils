package com.xuan.hitools.hiweb;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2022/8/31
 */
@Component
public class HiWebDBHelper implements InitializingBean {

    private JdbcTemplate jdbcTemplate;

    private final static String SQL_CREATE_TABLE = String.format(""
        + "CREATE TABLE IF NOT EXISTS %s("
        + "id bigint(20) unsigned NOT NULL AUTO_INCREMENT,"
        + "name varchar(128) NOT NULL,"
        + "content text NULL,"
        + "modify_time datetime NOT NULL,"
        + "PRIMARY KEY (`id`),"
        + "KEY idx_name (`name`)"
        + ")", "hb_page");

    @Override
    public void afterPropertiesSet() throws Exception {
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dds.setUsername("bpmweb");
        dds.setPassword("123456");
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        jdbcTemplate = new JdbcTemplate(dds);
        jdbcTemplate.update(SQL_CREATE_TABLE);
    }



}
