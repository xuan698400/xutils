package com.xuan.moho.base.log;

/**
 * @author xuan
 * @since 2020/10/19
 */
public interface Logger {

    void error(String msg, Throwable t);

    void error(String msg);

    void info(String msg);
}