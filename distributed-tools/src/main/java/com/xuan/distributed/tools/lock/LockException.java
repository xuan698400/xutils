package com.xuan.distributed.tools.lock;

/**
 * @author xuan
 * @date 2018/1/10
 */
public class LockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LockException(String message) {
        super(message);
    }

    public LockException(Throwable cause) {
        super(cause);
    }

}
