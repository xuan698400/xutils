package com.xuan.mix.bt.diverter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuan
 * @since 2020/11/20
 */
public class MainTest {

    public static void main(String[] args) {

        //一般从配置中心获取，下面表示切10%流量到新链路
        DiverterConfig config = getConfig();

        System.out.println(Diverter.isHit(103L, config));
        System.out.println(Diverter.isHit(107L, config));
        System.out.println(Diverter.isHit(123L, config));
        System.out.println(Diverter.isHit(1103L, config));
        System.out.println(Diverter.isHit(1234L, config));
        System.out.println(Diverter.isHit(5678L, config));
    }

    private static DiverterConfig getConfig() {
        DiverterConfig config = new DiverterConfig();
        config.setHitNum(10);
        config.setTotalNum(100);
        Set<String> blackSet = new HashSet<>();
        blackSet.add("1234");
        config.setBlackSet(blackSet);
        Set<String> whiteSet = new HashSet<>();
        whiteSet.add("5678");
        config.setWhiteSet(whiteSet);
        return config;
    }

}
