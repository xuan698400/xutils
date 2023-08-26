package com.xuan.xutils.sequence;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by xuan on 2018/1/10.
 */
public class DbTest_Api extends BaseTest {

    private Sequence sequence;

    @Before
    public void setup() {
        sequence = initDbSequence("DbTest_Api");
    }

    @Test
    public void test() {
        testRun(sequence);
    }
}
