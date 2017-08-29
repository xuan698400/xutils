package com.xuan.xutils.concurrent.forkjoin.listtask.core;

/**
 * 执行ListTask异常
 * <p>
 * Created by xuan on 17/8/29.
 */
public class ListTaskException extends Throwable {
    private static final long serialVersionUID = 1;

    /**
     * 真实抛出的异常，方便用户对不用异常进行处理
     */
    private Throwable realException;

    public ListTaskException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.realException = cause;
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

    public Throwable getRealException() {
        return realException;
    }

    public void setRealException(Throwable realException) {
        this.realException = realException;
    }
}
