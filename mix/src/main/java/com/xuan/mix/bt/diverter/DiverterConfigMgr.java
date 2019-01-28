package com.xuan.mix.bt.diverter;

/**
 * 配置管理器
 * Created by xuan on 2019/1/29.
 */
public interface DiverterConfigMgr {

    /**
     * 获取配置
     *
     * @param name 分流器名称
     * @return 配置
     */
    DiverterConfig getConfig(String name);

    /**
     * 设置配置，新配置会覆盖老配置
     *
     * @param name   分流器名称
     * @param config 新配置
     */
    void putConfig(String name, DiverterConfig config);

}
