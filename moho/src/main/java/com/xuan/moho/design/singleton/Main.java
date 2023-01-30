package com.xuan.moho.design.singleton;

/**
 * @author xuan
 * @since 2023/1/29
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Singleton7 singleton7 = SingletonPool.get(Singleton7.class);
    }

}
