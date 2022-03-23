package com.xuan.mix.bt.etlflow.tosql;

import com.xuan.mix.bt.etlflow.model.EtlFlow;

/**
 * @author xuan
 * @since 2022/3/17
 */
public interface ToSqlHandler {

    String toSql(EtlFlow etlFlow);
}
