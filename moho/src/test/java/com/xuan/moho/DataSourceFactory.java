package com.xuan.moho;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author xuan
 * @since 2023/1/7
 */
public class DataSourceFactory {

    public static DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("bpmweb");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return druidDataSource;
    }

}
