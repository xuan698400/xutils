package com.xuan.crudboy.domain;

import com.xuan.crudboy.config.model.CbTableFieldConfig;

/**
 * @author xuan
 * @since 2023/11/9
 */
public class CbTableField {

    private Object value;

    private CbTableFieldConfig config;

    public static CbTableField of(CbTableFieldConfig config, Object value) {
        CbTableField field = new CbTableField();
        field.setConfig(config);
        field.setValue(value);
        return field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public CbTableFieldConfig getConfig() {
        return config;
    }

    public void setConfig(CbTableFieldConfig config) {
        this.config = config;
    }

}
