package com.xuan.hisql.service;

import com.xuan.common.model.page.PageData;
import com.xuan.common.model.page.PageQuery;
import com.xuan.hisql.service.model.DataSource;
import com.xuan.hisql.service.model.DataSourceCondition;

/**
 * @author xuan
 * @since 2022/8/23
 */
public interface DataSourceService {

    Long add(DataSource dataSource);

    void modify(DataSource dataSource);

    void remove(Long id);

    PageData<DataSource> search(DataSourceCondition condition, PageQuery pageQuery);
}
