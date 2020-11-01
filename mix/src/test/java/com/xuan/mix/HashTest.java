package com.xuan.mix;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuan
 * @since 2020/3/12
 */
public class HashTest {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        System.out.println("start");

        map.computeIfAbsent("AaAa", (k) -> {

            String BBBBValue = map.computeIfAbsent("BBBB", (k2) -> {
                return "1";
            });

            return BBBBValue;
        });

        //map.computeIfAbsent("AaAa", (k) -> map.computeIfAbsent("BBBB", (k2) -> "1"));
        System.out.println("end");
    }

    public void test() {
        System.out.println("dddddddd");
    }
}
