package com.xuan.dbconsole.domain.repository;

import com.xuan.dbconsole.domain.model.DataSource;
import com.xuan.dbconsole.domain.model.PageQuery;
import com.xuan.dbconsole.domain.model.PageResult;

/**
 * @author xuan
 * @since 2022/12/8
 */
public interface DataSourceRepository {

    PageResult<DataSource> page(PageQuery pageQuery);

    DataSource getById(Long id);
}
