package com.xuan.dao.sql;

import java.lang.reflect.Field;

import com.xuan.dao.model.BaseDO;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class PrimaryKeyHelper {

    public static Object getAndCheckPrimaryKeyValue(BaseDO baseDO){
        Object primaryKeyValue = PrimaryKeyHelper.getPrimaryKeyValue(baseDO);
        if (null == primaryKeyValue) {
            throw new IllegalArgumentException("primaryKeyValue is null.");
        }
        return primaryKeyValue;
    }

    private static Object getPrimaryKeyValue(BaseDO baseDO) {
        Field[] fields = baseDO.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(baseDO.primaryKey())) {
                field.setAccessible(true);
                try {
                    return field.get(baseDO);
                } catch (IllegalAccessException e) {
                    //Ingore
                }
            }
        }
        return null;
    }

}
