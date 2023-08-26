package com.xuan.xutils.design.singleton;

/**
 * 懒汉式(线程不安全)
 *
 * @author xuan
 * @since 2023/1/29
 */
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (null == instance) {
            instance = new Singleton2();
        }
        return instance;
    }
}
