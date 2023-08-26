package com.xuan.xutils.design.singleton;

/**
 * 懒汉式(线程安全)
 *
 * @author xuan
 * @since 2023/1/29
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (null == instance) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
