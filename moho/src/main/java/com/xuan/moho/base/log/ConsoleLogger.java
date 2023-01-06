package com.xuan.moho.base.log;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class ConsoleLogger implements Logger {
    @Override
    public void error(String msg, Throwable t) {
        System.out.println("error:" + msg + ", t=" + t);
    }

    @Override
    public void error(String msg) {
        System.out.println("error:" + msg);
    }

    @Override
    public void info(String msg) {
        System.out.println("info:" + msg);
    }

}
