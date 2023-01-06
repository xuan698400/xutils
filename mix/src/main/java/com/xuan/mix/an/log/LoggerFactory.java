package com.xuan.mix.an.log;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class LoggerFactory {

    public static Logger getLogger() {
        return new ConsoleLogger();
    }

}
