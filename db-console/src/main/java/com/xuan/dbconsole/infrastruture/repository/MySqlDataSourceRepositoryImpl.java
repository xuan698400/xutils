package com.xuan.dbconsole.infrastruture.repository;

import com.xuan.dbconsole.common.Bean;
import com.xuan.dbconsole.domain.model.DataSource;
import com.xuan.dbconsole.domain.model.PageQuery;
import com.xuan.dbconsole.domain.model.PageResult;
import com.xuan.dbconsole.domain.repository.DataSourceRepository;

/**
 * @author xuan
 * @since 2022/12/8
 */
@Bean
public class MySqlDataSourceRepositoryImpl implements DataSourceRepository {

    @Override
    public PageResult<DataSource> page(PageQuery pageQuery) {
        return null;
    }

    @Override
    public DataSource getById(Long id) {
        DataSource dataSource = new DataSource();
        dataSource.setId(0L);
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        return dataSource;
    }

}
