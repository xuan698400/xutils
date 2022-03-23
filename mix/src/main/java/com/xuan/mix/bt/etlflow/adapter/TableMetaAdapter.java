package com.xuan.mix.bt.etlflow.adapter;

import java.util.List;

/**
 * @author xuan
 * @since 2022/3/17
 */
public interface TableMetaAdapter {

    List<String> columnNameList(String tableName, String dataSource);
}
