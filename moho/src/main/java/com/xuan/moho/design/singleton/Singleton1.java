package com.xuan.moho.design.singleton;

/**
 * 饿汉式
 *
 * @author xuan
 * @since 2023/1/29
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
