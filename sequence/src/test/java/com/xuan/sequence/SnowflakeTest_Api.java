package com.xuan.sequence;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by xuan on 2018/5/9.
 */
public class SnowflakeTest_Api extends BaseTest {

    private Sequence sequence;

    @Before
    public void setup() {
        sequence = getSnowflakeSequence();
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println("++++++++++id:" + sequence.nextValue());
        }
        System.out.println("interval time:" + (System.currentTimeMillis() - start));
    }
}
