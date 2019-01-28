package com.xuan.mix.bt.diverter.impl;

import com.xuan.mix.bt.diverter.DiverterConfig;
import com.xuan.mix.bt.diverter.DiverterConfigMgr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置工厂默认实现
 * Created by xuan on 2019/1/29.
 */
public class DefaultDiverterConfigMgr implements DiverterConfigMgr {

    private Map<String, DiverterConfig> name2ConfigMap = new ConcurrentHashMap<>();

    @Override
    public DiverterConfig getConfig(String name) {
        return name2ConfigMap.get(name);
    }

    @Override
    public void putConfig(String name, DiverterConfig config) {
        name2ConfigMap.put(name, config);
    }

}
