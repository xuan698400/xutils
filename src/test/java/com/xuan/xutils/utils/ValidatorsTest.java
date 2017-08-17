package com.xuan.xutils.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuan on 17/6/7.
 */
public class ValidatorsTest {

    @Test
    public void testIsBlank(){
        Assert.assertTrue(Validators.isBlank(null));
        Assert.assertTrue(Validators.isBlank(""));
        Assert.assertTrue(Validators.isBlank(" "));
        Assert.assertTrue(!Validators.isBlank("bob"));
        Assert.assertTrue(!Validators.isBlank(" bob "));
    }
}
