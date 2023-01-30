package com.xuan.moho.design.singleton;

/**
 * 懒汉式(线程安全)
 *
 * @author xuan
 * @since 2023/1/29
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {
    }

    public static synchronized Singleton3 getInstance() {
        if (null == instance) {
            instance = new Singleton3();
        }
        return instance;
    }
}
