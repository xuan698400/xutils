package com.extp.framework.core.log;

/**
 * 为了方便不同的系统日志系统差异，这里做一层适配方便快速兼容
 *
 * @author xuan
 * @since 2021/2/25
 */
public interface LogAdapter {
    void error(String msg, Throwable e);

    void error(String msg);

    void warn(String msg);
}
