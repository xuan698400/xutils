package com.xuan.xutils.lock;

/**
 * 分布式锁异常
 *
 * @author xuan
 * @date 2018/1/10
 */
public class DistributedLockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(Throwable cause) {
        super(cause);
    }

}
