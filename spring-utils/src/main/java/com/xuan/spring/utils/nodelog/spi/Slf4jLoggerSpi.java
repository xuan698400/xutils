package com.xuan.spring.utils.nodelog.spi;

import com.xuan.mix.bt.nodelog.spi.LoggerSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuan
 * @since 2021/9/3
 */
public class Slf4jLoggerSpi implements LoggerSpi {

    private final static Logger log = LoggerFactory.getLogger("NodeLog_Slf4jLoggerSpi_Logger");

    @Override
    public void info(String msg) {
        log.info(msg);
    }

}
