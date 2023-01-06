package com.xuan.moho.base.log;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return new ConsoleLogger();
    }

}
