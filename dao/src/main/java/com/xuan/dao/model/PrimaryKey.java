package com.xuan.dao.model;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class PrimaryKey {

    private String name;

    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static PrimaryKey of(String name, Object value) {
        PrimaryKey primaryKey = new PrimaryKey();
        primaryKey.setName(name);
        primaryKey.setValue(value);
        return primaryKey;
    }

}
