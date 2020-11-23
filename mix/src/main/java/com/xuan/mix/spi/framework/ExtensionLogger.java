package com.xuan.mix.spi.framework;

/**
 * @author xuan
 * @since 2020/11/22
 */
public interface ExtensionLogger {

    void error(String msg, Throwable e);

    void warn(String msg);
}
