package com.xuan.mix;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author xuan
 * @since 2021/1/7
 */
public class Test {

    public static void main(String[] args) throws Exception {
        URL[] urls = new URL[]{new URL("/Users/xuan/Desktop/code/github/xutils/mix/target/classes/com/xuan/mix/bt/diverter")};

        URLClassLoader ucl1 = new URLClassLoader(urls);



        System.out.println(Test.class.getProtectionDomain().getCodeSource().getLocation().toURI());
    }
}
