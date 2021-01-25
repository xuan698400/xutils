package com.xuan.ext.framework.core.log;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class LogAdapterFactory {

    private static LogAdapter logAdapter = new DefaultLogAdapter();

    public static LogAdapter getLogAdapter() {
        return logAdapter;
    }

    public static void setLogAdapter(LogAdapter logAdapter) {
        LogAdapterFactory.logAdapter = logAdapter;
    }

}
