package com.extp.framework.core.log;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class LogAdapterFactory {

    private static LogAdapter logAdapter = new ConsoleLogAdapter();

    public static LogAdapter getLogAdapter() {
        return logAdapter;
    }

    public static void setLogAdapter(LogAdapter logAdapter) {
        LogAdapterFactory.logAdapter = logAdapter;
    }
}
