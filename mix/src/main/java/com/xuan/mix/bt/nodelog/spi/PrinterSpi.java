package com.xuan.mix.bt.nodelog.spi;

/**
 * 打印，例如同步还是异步
 *
 * @author xuan
 * @since 2021/9/3
 */
public interface PrinterSpi {

    /**
     * 打印输出
     *
     * @param loggerSpi 日志输出的具体实现
     * @param msg       日志内容
     */
    void print(LoggerSpi loggerSpi, String msg);
}
