package com.xuan.mix.bt.etlflow.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuan
 * @since 2022/3/17
 */
public class MockTableMetaAdapter implements TableMetaAdapter {

    @Override
    public List<String> columnNameList(String tableName, String dataSource) {
        List<String> list = new ArrayList<>();

        if ("table1".equals(tableName)) {
            list.add("a1");
            list.add("b1");
            list.add("c1");
            list.add("d1");
        }

        return list;
    }

}
