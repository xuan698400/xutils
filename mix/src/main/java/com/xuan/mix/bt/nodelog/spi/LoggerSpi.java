package com.xuan.mix.bt.nodelog.spi;

/**
 * 日志实现扩展，不同系统可能会使用不同的日志打印框架，实现该接口可以自定义
 *
 * @author xuan
 * @since 2021/9/3
 */
public interface LoggerSpi {

    /**
     * 日志输出
     *
     * @param msg 日志
     */
    void info(String msg);
}
