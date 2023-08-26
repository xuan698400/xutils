package com.xuan.xutils.sequence;

import com.xuan.xutils.sequence.common.UUIDUtils;
import org.junit.Test;

/**
 * Created by xuan on 2018/6/7.
 */
public class UUIDTest_Api extends BaseTest {

    @Test
    public void testUuid() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println("++++++++++id:" + UUIDUtils.uuid());
        }
        System.out.println("interval time:" + (System.currentTimeMillis() - start));
    }
}
