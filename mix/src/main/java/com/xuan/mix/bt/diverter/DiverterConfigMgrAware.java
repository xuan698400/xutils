package com.xuan.mix.bt.diverter;

/**
 * 配置管理器可设置接口
 * Created by xuan on 2019/1/29.
 */
public interface DiverterConfigMgrAware {

    /**
     * 设置配置工厂
     *
     * @param configMgr 配置管理器
     */
    void setConfigMgr(DiverterConfigMgr configMgr);
}
