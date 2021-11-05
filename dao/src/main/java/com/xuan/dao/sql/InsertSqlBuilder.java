package com.xuan.dao.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class InsertSqlBuilder {

    public static InsertSql build(BaseDO baseDO) {
        InsertSql insertSql = new InsertSql();

        List<Object> valueList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("INSERT INTO " + baseDO.tableName() + "(");
        int paramsSize = 0;

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
                sb.append(field.getName());
                sb.append(",");
                valueList.add(value);
                paramsSize++;
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES(");
        for (int i = 0; i < paramsSize; i++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        Object[] params = new Object[valueList.size()];
        valueList.toArray(params);

        insertSql.setSql(sb.toString());
        insertSql.setParams(params);
        return insertSql;
    }

}
