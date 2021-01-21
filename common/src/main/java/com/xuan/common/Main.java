package com.xuan.common;

/**
 * @author xuan
 * @since 2020/3/9
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Main.class.getResource(""));
        System.out.println(Main.class.getClassLoader().getResource(""));

        System.out.println(Main.class.getResource("/"));
        System.out.println(Main.class.getClassLoader().getResource("/"));
    }
}
