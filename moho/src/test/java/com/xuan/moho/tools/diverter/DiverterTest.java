package com.xuan.moho.tools.diverter;

import java.util.HashSet;
import java.util.Set;

import com.xuan.moho.tools.diverter.core.DefaultDiverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/25
 */
public class DiverterTest {

    private Diverter diverter;

    @Before
    public void init() {
        //这里的参数一般配置中心配置后，再进行分流器的初始化
        Set<Long> blackList = new HashSet<>();
        blackList.add(101L);
        Set<Long> whiteList = new HashSet<>();
        whiteList.add(5678L);
        diverter = DefaultDiverter.builder().hitNum(10).totalNum(100).blackList(blackList).whiteList(whiteList).build();
    }

    @Test
    public void test() {
        Assert.assertEquals(diverter.isHit(103L), Boolean.TRUE);
        System.out.println(diverter.isHit(103L));

        System.out.println(diverter.isHit(107L));
        Assert.assertEquals(diverter.isHit(107L), Boolean.TRUE);

        System.out.println(diverter.isHit(123L));
        Assert.assertEquals(diverter.isHit(123L), Boolean.FALSE);

        System.out.println(diverter.isHit(1103L));
        Assert.assertEquals(diverter.isHit(1103L), Boolean.TRUE);

        System.out.println(diverter.isHit(101L));
        Assert.assertEquals(diverter.isHit(101L), Boolean.FALSE);

        System.out.println(diverter.isHit(5678L));
        Assert.assertEquals(diverter.isHit(5678L), Boolean.TRUE);
    }

}
