package com.xuan.mix.bt.diverter.impl;

import com.xuan.mix.bt.diverter.Diverter;
import com.xuan.mix.bt.diverter.DiverterConfig;
import com.xuan.mix.bt.diverter.DiverterConfigMgr;

import java.util.Set;

/**
 * 分流器默认实现
 * Created by xuan on 2019/1/29.
 */
public class DefaultDiverter implements Diverter {

    private DiverterConfigMgr configMgr;

    @Override
    public boolean isHit(String name, long bizId) {
        return hitNum(name, bizId) >= 0;
    }

    public int hitNum(String name, long bizId) {
        DiverterConfig config = getAndCheckConfig(name);

        //黑名单验证
        Set<String> blackSet = config.getBlackSet();
        if (null != blackSet && !blackSet.isEmpty()) {
            // 命中黑名单
            if (blackSet.contains(String.valueOf(bizId))) {
                return -1;
            }
        }

        //白名单验证
        Set<String> whiteSet = config.getWhiteSet();
        if (null != whiteSet && !whiteSet.isEmpty()) {
            // 命中白名单
            if (whiteSet.contains(String.valueOf(bizId))) {
                // 返回0号桶
                return 0;
            }
        }

        //分流比例小于0, 全部流量不走bts
        int percent = config.getPercent();
        if (percent <= 0) {
            return -1;
        }

        //计算hash桶
        long bucket = bizId % config.getTotal();

        if (bucket < 0) {
            bucket = Math.abs(bucket);
        }

        // 命中bts分桶
        if (bucket < percent) {
            return (int) bucket;
        }

        // 未命中分桶
        return -1;
    }

    private DiverterConfig getAndCheckConfig(String name) {
        if (null == name || name.trim().length() == 0) {
            throw new IllegalArgumentException("name is empty.");
        }

        if (null == configMgr) {
            throw new IllegalArgumentException("configFactory is null.");
        }

        DiverterConfig config = configMgr.getConfig(name);
        if (null == config) {
            throw new IllegalArgumentException("config is not exsit. name=" + name);
        }

        if (null == config.getPercent() || config.getPercent() < 0) {
            throw new IllegalArgumentException("percent must be [0,Max.int].");
        }
        if (null == config.getTotal() || config.getTotal() < 0) {
            throw new IllegalArgumentException("total must be [0,Max.int].");
        }
        return config;
    }

    @Override
    public void setConfigMgr(DiverterConfigMgr configMgr) {
        this.configMgr = configMgr;
    }

}
