package com.xuan.xutils.sql.orm.resultsetmapping;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.xuan.xutils.sql.executer.ResultSetMapping;

/**
 * 返回map集合结果映射实现
 *
 * @author xuan
 * @since 2023/1/11
 */
public class MapResultSetMapping implements ResultSetMapping<Map<String, Object>> {

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
