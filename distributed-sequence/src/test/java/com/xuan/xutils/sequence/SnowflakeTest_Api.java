package com.xuan.xutils.sequence;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by xuan on 2018/5/9.
 */
public class SnowflakeTest_Api extends BaseTest {

    private Sequence sequence;

    @Before
    public void setup() {
        sequence = initSnowflakeSequence();
    }

    @Test
    public void test() {
        testRun(sequence);
    }

}
