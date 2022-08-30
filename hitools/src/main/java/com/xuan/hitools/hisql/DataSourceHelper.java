package com.xuan.hitools.hisql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2022/8/30
 */
@Component
public class DataSourceHelper implements InitializingBean {

    private Map<String, DataSource> code2DataSourceMap = new HashMap<>();
    private Map<String, JdbcTemplate> code2JdbcTemplateMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        addDataSource();
        initDataSource();
    }

    private void addDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setCode("bpmweb");
        dataSource.setName("bpmweb数据库");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        code2DataSourceMap.put(dataSource.getCode(), dataSource);
    }

    private void initDataSource() throws Exception {
        for (DataSource dataSource : code2DataSourceMap.values()) {
            DruidDataSource dds = new DruidDataSource();
            dds.setUrl(dataSource.getUrl());
            dds.setUsername(dataSource.getUsername());
            dds.setPassword(dataSource.getPassword());
            dds.setDriverClassName("com.mysql.jdbc.Driver");
            code2JdbcTemplateMap.put(dataSource.getCode(), new JdbcTemplate(dds));
        }
    }

    public JdbcTemplate getJdbcTemplateByCode(String code) {
        return code2JdbcTemplateMap.get(code);
    }

    public List<DataSource> getAllDataSource() {
        return new ArrayList<>(code2DataSourceMap.values());
    }

}
