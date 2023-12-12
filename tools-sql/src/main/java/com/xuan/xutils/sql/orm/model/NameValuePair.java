package com.xuan.xutils.sql.orm.model;

import java.security.InvalidParameterException;

/**
 * 键值对模型
 *
 * @author xuan
 * @since 2021/11/13
 */
public class NameValuePair {

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

    public static NameValuePair of(String name, Object value) {
        NameValuePair pair = new NameValuePair();
        pair.setName(name);
        pair.setValue(value);
        return pair;
    }

    public void checkValid() {
        if (null == name || name.trim().length() == 0) {
            throw new InvalidParameterException("primaryKey name is empty.");
        }

        if (null == value) {
            throw new InvalidParameterException("primaryKey value is null.");
        }
    }

    public boolean isValid() {
        return (null != name && name.trim().length() > 0) && null != value;
    }

}
