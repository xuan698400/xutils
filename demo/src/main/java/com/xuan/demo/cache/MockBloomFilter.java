package com.xuan.demo.cache;

/**
 * @author xuan
 * @since 2023/12/11
 */
public class MockBloomFilter {

    public final static Byte[] BITS = new Byte[20];

    private static int hash1(String str) {
        return str.hashCode() % 20;
    }

    private static int hash2(String str) {
        return str.hashCode() % 20;
    }

    private static int hash3(String str) {
        return str.hashCode() % 20;
    }

    public static void main(String[] args) {

    }
}
