package com.xuan.mix.bt.nodelog.spi;

/**
 * 日志答应扩展，可根据不同的系统使用不同的日志框架
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
