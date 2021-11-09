package com.xuan.dao;

import java.util.List;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public interface Dao {

    void insert(List<BaseDO> dataList);

    void update(List<BaseDO> dataList);

    void delete(List<BaseDO> dataList);

    <T extends BaseDO> List<T> select(List<T> conditionList, Class<T> elementType);

    <T extends BaseDO> List<T> select(String sql, Object[] params, Class<T> elementType);
}
