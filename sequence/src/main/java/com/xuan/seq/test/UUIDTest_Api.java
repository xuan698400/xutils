package com.xuan.seq.test;

import com.xuan.seq.utils.UUIDUtils;
import org.junit.Test;

/**
 * Created by xuan on 2018/6/7.
 */
public class UUIDTest_Api {

    @Test
    public void testUuid() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println("++++++++++id:" + UUIDUtils.uuid());
        }
        System.out.println("interval time:" + (System.currentTimeMillis() - start));
    }
}
