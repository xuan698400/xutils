package com.xuan.ext.framework.core.log;

/**
 * @author xuan
 * @since 2021/1/25
 */
public interface LogAdapter {

    void error(String msg, Throwable e);

    void error(String msg);

    void warn(String msg);
}
