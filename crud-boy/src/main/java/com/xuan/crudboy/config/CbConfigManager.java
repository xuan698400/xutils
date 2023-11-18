package com.xuan.crudboy.config;

import com.xuan.crudboy.config.model.CbTableConfig;
import com.xuan.crudboy.domain.CbTableField;

/**
 * @author xuan
 * @since 2023/11/10
 */
public interface CbConfigManager {

    void init();

    CbTableConfig getTableConfig(String tableName);

    CbTableField getTableFieldConfig(String tableName, String fieldName);

}
