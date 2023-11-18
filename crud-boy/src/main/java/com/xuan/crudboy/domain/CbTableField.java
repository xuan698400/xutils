package com.xuan.crudboy.domain;

import com.xuan.crudboy.config.model.CbTableFieldConfig;

/**
 * @author xuan
 * @since 2023/11/9
 */
public class CbTableField {

    private Object value;

    private CbTableFieldConfig config;

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
