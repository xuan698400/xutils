package com.xuan.moho.framework.extension.framework.core;

/**
 * @author xuan
 * @since 2021/8/16
 */
public class LogUtil {

    public static void error(String msg, Throwable t) {
        System.out.println(msg + ", t=" + t);
    }

    public static void error(String msg) {
        System.out.println(msg);
    }

}
