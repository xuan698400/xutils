package com.xuan.mix.log;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class ConsoleLogAdapterImpl implements LogAdapter {
    @Override
    public void error(String msg, Throwable t) {
        System.out.println(msg + ", t=" + t);
    }

    @Override
    public void error(String msg) {
        System.out.println(msg);
    }

}
