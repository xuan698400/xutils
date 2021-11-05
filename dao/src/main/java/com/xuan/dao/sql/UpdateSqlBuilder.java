package com.xuan.dao.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class UpdateSqlBuilder {

    public static UpdateSql build(BaseDO baseDO) {
        UpdateSql updateSql = new UpdateSql();

        List<Object> valueList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("UPDATE " + baseDO.tableName() + " SET ");
        Object primaryKeyValue = null;

        Field[] fields = baseDO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(baseDO);
            } catch (IllegalAccessException e) {
                //Ignore
            }

            String fieldName = field.getName();

            if (fieldName.equals(baseDO.primaryKey())) {
                primaryKeyValue = value;
            } else {
                //主键不需要更新
                if (null != value) {
                    sb.append(fieldName);
                    sb.append("=?,");
                    valueList.add(value);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE ");
        sb.append(baseDO.primaryKey());
        sb.append("=?");
        valueList.add(primaryKeyValue);

        Object[] params = new Object[valueList.size()];
        valueList.toArray(params);

        updateSql.setSql(sb.toString());
        updateSql.setParams(params);

        return updateSql;
    }

}
