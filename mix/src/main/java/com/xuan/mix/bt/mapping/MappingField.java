package com.xuan.mix.bt.mapping;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author xuan
 * @since 2021/9/15
 */
public class MappingField {

    private Object value;

    private Class type;

    public static MappingField of(Object obj, String fieldName) {
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            MappingField mappingField = new MappingField();
            mappingField.setValue(field.get(obj));
            mappingField.setType(field.getType());
            return mappingField;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new MappingException(e);
        }
    }

    public boolean isValuePrimitive() {
        if (type.isPrimitive()) {
            return true;
        }
        if (value instanceof Number) {
            return true;
        }
        if (value instanceof Date) {
            return true;
        }
        if (value instanceof String) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MappingField{" +
            "value=" + value +
            ", type=" + type +
            '}';
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

}
