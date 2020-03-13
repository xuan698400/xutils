package com.xuan.mix;

/**
 * @author xuan
 * @since 2020/3/12
 */
public class HashTest {

    public static Long toHash(String str) {
        return Long.valueOf(String.valueOf(str.hashCode()));
    }

    public static void main(String[] args) {
        System.out.println(toHash("c_3001"));
    }
}
