package com.xuan.mix.log;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class LogAdapterFactory {

    public static LogAdapter getLogAdapter() {
        return new ConsoleLogAdapterImpl();
    }
}
