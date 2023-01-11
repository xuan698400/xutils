package com.xuan.moho.sql.orm.core;

import java.util.List;

import com.xuan.moho.base.model.page.PageData;
import com.xuan.moho.base.model.page.PageQuery;
import com.xuan.moho.sql.orm.Dao;
import com.xuan.moho.sql.orm.model.DataModel;
import com.xuan.moho.sql.orm.sqlparams.SQLParamsQueryCreator;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class DefaultDao implements Dao {

    @Override
    public long insertBackId(DataModel dataModel) {
        return 0;
    }

    @Override
    public int insert(DataModel dataModel) {
        return 0;
    }

    @Override
    public int update(DataModel dataModel) {
        return 0;
    }

    @Override
    public int delete(DataModel dataModel) {
        return 0;
    }

    @Override
    public int update(SQLParamsQueryCreator sqlCreator) {
        return 0;
    }

    @Override
    public <T extends DataModel> List<T> select(DataModel dataModel, Class<T> elementType) {
        return null;
    }

    @Override
    public <T extends DataModel> List<T> select(SQLParamsQueryCreator creator, Class<T> elementType) {
        return null;
    }

    @Override
    public Long count(SQLParamsQueryCreator sqlCreator) {
        return null;
    }

    @Override
    public <T extends DataModel> PageData<T> selectPage(SQLParamsQueryCreator creator, PageQuery pageQuery,
        Class<T> elementType) {
        return null;
    }
    
}
