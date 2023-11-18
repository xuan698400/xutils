package com.xuan.crudboy.config.model;

import java.util.List;

/**
 * @author xuan
 * @since 2023/11/5
 */
public class CbConfig {

    private List<CbTableConfig> tables;

    public List<CbTableConfig> getTables() {
        return tables;
    }

    public void setTables(List<CbTableConfig> tables) {
        this.tables = tables;
    }

}
