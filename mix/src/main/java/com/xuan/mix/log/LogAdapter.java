package com.xuan.mix.log;

/**
 * @author xuan
 * @since 2020/10/19
 */
public interface LogAdapter {

    void error(String msg, Throwable t);

    void error(String msg);
}
