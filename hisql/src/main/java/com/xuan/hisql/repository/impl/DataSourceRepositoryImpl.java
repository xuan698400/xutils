package com.xuan.hisql.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.hisql.config.DataSourceConfig;
import com.xuan.hisql.repository.contertor.DataSourceDoToPoConvertor;
import com.xuan.hisql.repository.DataSourceRepository;
import com.xuan.hisql.service.model.DataSourceCondition;
import com.xuan.hisql.repository.model.DataSourcePO;
import com.xuan.hisql.service.model.DataSource;
import com.xuan.mix.an.common.model.page.PageData;
import com.xuan.mix.an.common.model.page.PageQuery;
import com.xuan.mix.an.common.utils.CollectionUtils;
import com.xuan.mix.an.common.utils.StringUtils;
import com.xuan.mix.an.dao.Dao;
import com.xuan.mix.an.dao.sql.SqlCreator;
import com.xuan.spring.utils.jdbctemplate.JdbcTemplateDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

/**
 * @author xuan
 * @since 2022/8/25
 */
@Repository
public class DataSourceRepositoryImpl implements DataSourceRepository, InitializingBean {

    private Dao dao;

    @Resource
    private DataSourceConfig dataSourceConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        dao = new JdbcTemplateDao(dataSourceConfig.getDataSource());
    }

    @Override
    public Long add(DataSource dataSource) {
        DataSourcePO dataSourcePo = DataSourceDoToPoConvertor.toPo(dataSource);
        return dao.insertBackId(dataSourcePo);
    }

    @Override
    public void modify(DataSource dataSource) {
        DataSourcePO dataSourcePo = DataSourceDoToPoConvertor.toPo(dataSource);
        dao.update(dataSourcePo);
    }

    @Override
    public void remove(Long id) {
        DataSourcePO dataSourcePo = new DataSourcePO();
        dataSourcePo.setId(id);
        dao.delete(dataSourcePo);
    }

    @Override
    public PageData<DataSource> search(DataSourceCondition condition, PageQuery pageQuery) {

        SqlCreator creator = SqlCreator.selectTable(new DataSourcePO().tableName())
            .and("name like ?", "%" + condition.getSearchKey() + "%", StringUtils.isNotEmpty(condition.getSearchKey()))
            .andIn("id", condition.getIdList(), CollectionUtils.isNotEmpty(condition.getIdList()))
            .and("connect_status", condition.getConnectStatus(), StringUtils.isNotEmpty(condition.getConnectStatus()));
        PageData<DataSourcePO> pageData = dao.selectPage(creator, pageQuery, DataSourcePO.class);

        List<DataSource> dataSourceList = DataSourceDoToPoConvertor.toDomainList(pageData.getData());
        return PageData.of(dataSourceList, pageData.getTotalCount());
    }

}
