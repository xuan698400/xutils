package com.xuan.mix.utils;

import java.io.File;

import org.junit.Test;

/**
 * @author xuan
 * @since 2019/7/1
 */
public class LocalKvUtilsTest {

    private static final String path;

    static {
        path = System.getProperty("user.home") + File.separator + ".natureflow-idea.properties";
    }

    @Test
    public void testGet() {
        String install = LocalKvUtils.get(path, "install");
        System.out.println("==========>" + install);
    }

    @Test
    public void testPut() {
        LocalKvUtils.put(path, "install", "2");
        LocalKvUtils.put(path, "test", "200");
    }

    @Test
    public void testRemove() {
        LocalKvUtils.remove(path, "test");
    }

    @Test
    public void testRemoveAll() {
        LocalKvUtils.removeAll(path);
    }
    
}
