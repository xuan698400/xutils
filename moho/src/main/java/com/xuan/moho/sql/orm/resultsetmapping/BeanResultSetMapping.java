package com.xuan.moho.sql.orm.resultsetmapping;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.xuan.moho.base.exception.ExceptionFactory;
import com.xuan.moho.sql.executer.ResultSetMapping;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class BeanResultSetMapping<T> implements ResultSetMapping<T> {

    private Class<T> beanClass;

    public BeanResultSetMapping(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public T extract(ResultSet rs) throws SQLException {

        ResultSetMetaData me = rs.getMetaData();
        int size = me.getColumnCount();
        Map<String, Object> dataMap = new HashMap<>();

        Object bean;
        try {
            bean = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw ExceptionFactory.bizException("beanClass newInstance exception", e);
        }

        for (int i = 0; i < size; i++) {
            int index = i + 1;
            String columnName = me.getColumnName(index);
            Object value = rs.getObject(index);
            dataMap.put(columnName, value);
        }
        //return dataMap;

        return null;
    }

}
