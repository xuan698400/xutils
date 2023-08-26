package com.xuan.xutils.base.log;

/**
 * 通用日志接口，不强制使用，系统可以直接使用自带的日志框架，但是日志输出规范最好有
 * 可以使用Log构建，当然为了规范一些日志级别，使用通用Logger也不失为一种好的办法
 * 一般情况下，我们有这3个级别就够了：error、warn、info
 *
 * @author xuan
 * @since 2020/10/19
 */
public interface Logger {

    /**
     * 错误日志
     *
     * @param msg 日志
     * @param t   异常
     */
    void error(String msg, Throwable t);

    /**
     * 错误日志
     *
     * @param msg 日志
     */
    void error(String msg);

    /**
     * 错误日志
     *
     * @param log 规范日志模型
     * @param t   异常
     */
    void error(Log log, Throwable t);

    /**
     * 错误日志
     *
     * @param log 规范日志模型
     */
    void error(Log log);

    /**
     * 告警日志
     *
     * @param msg 日志
     */
    void warn(String msg);

    /**
     * 告警日志
     *
     * @param log 规范日志模型
     */
    void warn(Log log);

    /**
     * 提示日志
     *
     * @param msg 日志
     */
    void info(String msg);

    /**
     * 提示日志
     *
     * @param log 规范日志模型
     */
    void info(Log log);
}
