package com.xuan.crudboy.config.core;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.xuan.crudboy.config.CbConfigLoader;
import com.xuan.crudboy.config.CbConfigManager;
import com.xuan.crudboy.config.model.CbConfig;
import com.xuan.crudboy.config.model.CbTableConfig;
import com.xuan.crudboy.domain.CbTableField;
import com.xuan.crudboy.exception.CbAssert;

/**
 * @author xuan
 * @since 2023/11/10
 */
public class CbDefaultConfigManager implements CbConfigManager {

    private CbConfig config;

    private Map<String, CbTableConfig> tableConfigMap;

    private CbConfigLoader configLoader = new CbResourceConfigLoader();

    @Override
    public void init() {
        config = configLoader.load();
        tableConfigMap = config.getTables().stream().collect(
            Collectors.toMap(CbTableConfig::getName, Function.identity(), (k1, k2) -> k2));
    }

    @Override
    public CbTableConfig getTableConfig(String tableName) {
        CbAssert.notEmpty(tableName, "tableName不能为空");
        CbTableConfig tableConfig = tableConfigMap.get(tableName);
        CbAssert.notNull(tableConfig, String.format("表名[%s]配置不存在", tableName));
        return tableConfig;
    }

    @Override
    public CbTableField getTableFieldConfig(String tableName, String fieldName) {
        CbAssert.notEmpty(tableName, "tableName不能为空");
        CbAssert.notEmpty(fieldName, "fieldName不能为空");
        return null;
    }

}
