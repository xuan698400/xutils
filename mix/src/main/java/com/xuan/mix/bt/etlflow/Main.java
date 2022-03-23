package com.xuan.mix.bt.etlflow;

import com.xuan.mix.bt.etlflow.adapter.MockTableMetaAdapter;
import com.xuan.mix.bt.etlflow.adapter.TableMetaAdapter;
import com.xuan.mix.bt.etlflow.json2model.DefaultJsonToModelHandler;
import com.xuan.mix.bt.etlflow.json2model.JsonToModelHandler;
import com.xuan.mix.bt.etlflow.model.EtlFlow;
import com.xuan.mix.bt.etlflow.tosql.PostgreSqlHandler;
import com.xuan.mix.bt.etlflow.tosql.ToSqlHandler;

/**
 * @author xuan
 * @since 2022/3/17
 */
public class Main {

    private static JsonToModelHandler jsonToModelHandler = new DefaultJsonToModelHandler();

    private static ToSqlHandler toSqlHandler = new PostgreSqlHandler();

    private static TableMetaAdapter tableMetaAdapter = new MockTableMetaAdapter();

    public static void main(String[] args) {

        EtlFlow etlFlow = jsonToModelHandler.toModel(DefaultJsonToModelHandler.test_json);

        System.out.println(toSqlHandler.toSql(etlFlow));
    }

}
