package com.xuan.mix.bt.etlflow.json2model;

import com.xuan.mix.bt.etlflow.model.EtlFlow;

/**
 * @author xuan
 * @since 2022/3/17
 */
public interface JsonToModelHandler {

    EtlFlow toModel(String json);
}
