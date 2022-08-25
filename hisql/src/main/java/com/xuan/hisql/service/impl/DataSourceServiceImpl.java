package com.xuan.hisql.service.impl;

import javax.annotation.Resource;

import com.xuan.common.model.page.PageData;
import com.xuan.common.model.page.PageQuery;
import com.xuan.hisql.repository.DataSourceRepository;
import com.xuan.hisql.service.DataSourceService;
import com.xuan.hisql.service.model.DataSource;
import com.xuan.hisql.service.model.DataSourceCondition;
import org.springframework.stereotype.Service;

/**
 * @author xuan
 * @since 2022/8/23
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    private DataSourceRepository dataSourceRepository;

    @Override
    public Long add(DataSource dataSource) {
        return dataSourceRepository.add(dataSource);
    }

    @Override
    public void modify(DataSource dataSource) {
        dataSourceRepository.modify(dataSource);
    }

    @Override
    public void remove(Long id) {
        dataSourceRepository.remove(id);
    }

    @Override
    public PageData<DataSource> search(DataSourceCondition condition, PageQuery pageQuery) {
        return dataSourceRepository.search(condition, pageQuery);
    }

}
