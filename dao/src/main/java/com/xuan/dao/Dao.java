package com.xuan.dao;

import java.util.List;

import com.xuan.dao.common.DataModel;

/**
 * @author xuan
 * @since 2021/11/5
 */
public interface Dao {

    int insert(DataModel dataModel);

    int update(DataModel dataModel);

    int delete(DataModel dataModel);

    <T extends DataModel> List<T> select(DataModel dataModel, Class<T> elementType);

}
