package com.xuan.crudboy.config.model;

import java.util.List;

/**
 * @author xuan
 * @since 2023/11/5
 */
public class CbTableConfig {

    /**
     * 表名
     */
    private String name;

    /**
     * 字段列表
     */
    private List<CbTableFieldConfig> fieldConfigs;

    /**
     * 唯一键
     */
    private List<CbTableUkConfig> uks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CbTableFieldConfig> getFieldConfigs() {
        return fieldConfigs;
    }

    public void setFieldConfigs(List<CbTableFieldConfig> fieldConfigs) {
        this.fieldConfigs = fieldConfigs;
    }

    public List<CbTableUkConfig> getUks() {
        return uks;
    }

    public void setUks(List<CbTableUkConfig> uks) {
        this.uks = uks;
    }
    
}
