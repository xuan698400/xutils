package com.xuan.spring.utils.nodelog.spi.impl;

import com.xuan.spring.utils.nodelog.spi.LoggerSpi;

/**
 * @author xuan
 * @since 2021/9/3
 */
public class ConsoleLoggerSpi implements LoggerSpi {

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

}
