package com.xuan.mix.compile;

/**
 * 编译自定义异常
 *
 * @author xuan
 * @since 2020/10/19
 */
public class CompileException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CompileException(String message) {
        super(message);
    }

    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
