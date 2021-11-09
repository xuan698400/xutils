package com.xuan.dao.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class SelectSqlBuilder {

    public static SelectSql build(BaseDO baseDO) {

        StringBuilder sb = new StringBuilder("SELECT * FROM " + baseDO.tableName() + " WHERE");
        List<Object> valueList = new ArrayList<>();

        Field[] fields = baseDO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(baseDO);
            } catch (IllegalAccessException e) {
                //Ignore
            }

            if (null != value) {
                if (sb.toString().endsWith("WHERE")) {
                    sb.append(" ");
                } else {
                    sb.append(" AND ");
                }
                sb.append(field.getName());
                sb.append("=?");
                valueList.add(value);
            }
        }

        Object[] params = new Object[valueList.size()];
        valueList.toArray(params);

        SelectSql selectSql = new SelectSql();
        selectSql.setSql(sb.toString());
        selectSql.setParams(params);
        return selectSql;
    }

}
