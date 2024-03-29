package com.xuan.xutils.base.log;

/**
 * 获取日志工厂类
 *
 * @author xuan
 * @since 2020/10/19
 */
public class LoggerFactory {

    /**
     * 获取控制台输出的日志
     *
     * @param clazz 类
     * @return Logger
     */
    public static Logger getLogger(Class<?> clazz) {
        return new ConsoleLogger();
    }

}
