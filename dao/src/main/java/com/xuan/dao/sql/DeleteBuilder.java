package com.xuan.dao.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class DeleteBuilder {

    public static DeleteSql build(BaseDO baseDO) {
        DeleteSql deleteSql = new DeleteSql();

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
            }
        }

        List<Object> valueList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(
            "DELETE FROM " + baseDO.tableName() + " WHERE " + baseDO.primaryKey() + "=?");
        valueList.add(primaryKeyValue);

        Object[] params = new Object[valueList.size()];
        valueList.toArray(params);

        deleteSql.setSql(sb.toString());
        deleteSql.setParams(params);

        return deleteSql;
    }

}
