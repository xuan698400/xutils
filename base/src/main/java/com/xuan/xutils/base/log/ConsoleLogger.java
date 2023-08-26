package com.xuan.xutils.base.log;

/**
 * 控制台输出实现，真实的系统可以使用系统的日志框架做一层实现
 *
 * @author xuan
 * @since 2020/10/19
 */
public class ConsoleLogger implements Logger {
    @Override
    public void error(String msg, Throwable t) {
        System.out.println("error:" + msg + ", t=" + t);
    }

    @Override
    public void error(String msg) {
        System.out.println("error:" + msg);
    }

    @Override
    public void info(String msg) {
        System.out.println("info:" + msg);
    }

    @Override
    public void error(Log logModel, Throwable t) {
        System.out.println("error:" + logModel.toString() + ", t=" + t);
    }

    @Override
    public void error(Log logModel) {
        System.out.println("error:" + logModel.toString());
    }

    @Override
    public void warn(String msg) {
        System.out.println("warn:" + msg.toString());
    }

    @Override
    public void warn(Log log) {
        System.out.println("warn:" + log.toString());
    }

    @Override
    public void info(Log logModel) {
        System.out.println("info:" + logModel.toString());
    }

}
