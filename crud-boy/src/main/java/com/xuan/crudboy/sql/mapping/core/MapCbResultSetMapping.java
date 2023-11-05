package com.xuan.crudboy.sql.mapping.core;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.xuan.crudboy.sql.mapping.CbResultSetMapping;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class MapCbResultSetMapping implements CbResultSetMapping<Map<String, Object>> {

    @Override
    public Map<String, Object> extract(ResultSet rs) throws SQLException {
        ResultSetMetaData me = rs.getMetaData();
        int size = me.getColumnCount();
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int index = i + 1;
            String columnName = me.getColumnName(index);
            Object value = rs.getObject(index);
            dataMap.put(columnName, value);
        }
        return dataMap;
    }

}
