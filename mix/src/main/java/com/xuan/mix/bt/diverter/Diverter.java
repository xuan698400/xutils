package com.xuan.mix.bt.diverter;

/**
 * 分流器
 * Created by xuan on 2019/1/29.
 */
public interface Diverter extends DiverterConfigMgrAware {

    /**
     * 是否命中进行分流
     *
     * @param name  分流器名称
     * @param bizId 业务id
     * @return true/false
     */
    boolean isHit(String name, long bizId);
}
