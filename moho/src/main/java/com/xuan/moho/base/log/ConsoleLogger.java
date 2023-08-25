package com.xuan.moho.base.log;

/**
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
    public void error(LogModel logModel, Throwable t) {
        System.out.println("error:" + logModel.toString() + ", t=" + t);
    }

    @Override
    public void error(LogModel logModel) {
        System.out.println("error:" + logModel.toString());
    }

    @Override
    public void info(LogModel logModel) {
        System.out.println("info:" + logModel.toString());
    }

}
