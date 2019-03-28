package com.xuan.mix.bt.diverter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xuan on 2019/1/29.
 */
public class DiverterTest {

    private Diverter diverter;

    @Before
    public void init() {
        //配置一般储存在配置中心，这里模拟设置
        DiverterConfig config = new DiverterConfig();
        config.setTotal(100);
        config.setPercent(20);
        Set<String> blackSet = new HashSet<>();
        blackSet.add("5");
        config.setBlackSet(blackSet);
        Set<String> whiteSet = new HashSet<>();
        whiteSet.add("80");
        config.setWhiteSet(whiteSet);

        //创建分流器
        diverter = new Diverter(config);
    }

    @Test
    public void test() {
        for (long i = 0; i < 100; i++) {
            boolean hit = diverter.isHit(i);
            System.out.println("i=" + i + ".hit=" + hit);
        }
    }

}
