package com.xuan.mix.compile;

/**
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
        //复写这个方法可以防止打出堆栈信息
        return this;
    }

}
