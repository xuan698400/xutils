package com.xuan.hisql.repository;

import com.xuan.common.model.page.PageData;
import com.xuan.common.model.page.PageQuery;
import com.xuan.hisql.service.model.DataSourceCondition;
import com.xuan.hisql.service.model.DataSource;

/**
 * @author xuan
 * @since 2022/8/25
 */
public interface DataSourceRepository {

    Long add(DataSource dataSource);

    void modify(DataSource dataSource);

    void remove(Long id);

    PageData<DataSource> search(DataSourceCondition condition, PageQuery pageQuery);
}
