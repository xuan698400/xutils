package com.xuan.dao;

import java.util.List;

import com.xuan.dao.common.DataModel;
import com.xuan.dao.common.PageData;
import com.xuan.dao.common.PageQuery;
import com.xuan.dao.sqlbuilder.SqlCreator;

/**
 * @author xuan
 * @since 2021/11/5
 */
public interface Dao {

    int insert(DataModel dataModel);

    int update(DataModel dataModel);

    int delete(DataModel dataModel);

    <T extends DataModel> List<T> select(DataModel dataModel, Class<T> elementType);

    <T extends DataModel> List<T> select(SqlCreator creator, Class<T> elementType);

    Long count(SqlCreator sqlCreator);

    <T extends DataModel> PageData<T> selectPage(SqlCreator creator, PageQuery pageQuery, Class<T> elementType);
}
