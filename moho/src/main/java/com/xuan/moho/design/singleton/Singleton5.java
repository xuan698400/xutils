package com.xuan.moho.design.singleton;

/**
 * 懒汉式(线程安全)
 *
 * @author xuan
 * @since 2023/1/29
 */
public class Singleton5 {
    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Singleton5 INSTANCE = new Singleton5();
    }
}
