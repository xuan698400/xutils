package com.xuan.hisql.config;

import javax.sql.DataSource;

/**
 * @author xuan
 * @since 2022/8/25
 */
public class DataSourceConfig {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
}
