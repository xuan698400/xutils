package com.extp.framework.core.log;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class ConsoleLogAdapter implements LogAdapter {

    @Override
    public void error(String msg, Throwable e) {
        System.out.println(String.format("ERROR: msg:%s", msg));
        e.printStackTrace();
    }

    @Override
    public void error(String msg) {
        System.out.println(String.format("ERROR: msg:%s", msg));
    }

    @Override
    public void warn(String msg) {
        System.out.println(String.format("WARN: msg:%s", msg));
    }
}
