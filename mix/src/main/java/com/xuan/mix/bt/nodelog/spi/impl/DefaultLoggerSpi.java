package com.xuan.mix.bt.nodelog.spi.impl;

import com.xuan.mix.bt.nodelog.spi.LoggerSpi;

/**
 * 默认System输出
 *
 * @author xuan
 * @since 2021/9/3
 */
public class DefaultLoggerSpi implements LoggerSpi {

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

}
