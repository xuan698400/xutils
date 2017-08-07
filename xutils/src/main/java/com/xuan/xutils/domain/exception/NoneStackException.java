package com.xuan.xutils.domain.exception;

/**
 * 很多时候我们在业务中使用自定义异常是用来控制流程,所以没有必要打出堆栈信息(打堆栈信息是比较消耗性能的)
 * 我们可以复写fillInStackTrace来处理
 * <p>
 * Created by xuan on 17/8/7.
 */
public class NoneStackException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoneStackException() {
        super();
    }

    public NoneStackException(String message) {
        super(message);
    }

    public NoneStackException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneStackException(Throwable cause) {
        super(cause);
    }

    protected NoneStackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 复写这个方法可以防止打出堆栈信息
     *
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
