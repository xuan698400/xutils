package com.xuan.distributed.tools.kvmap;

/**
 * @author xuan
 * @date 2018/1/10
 */
public class KvMapException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KvMapException(String message) {
        super(message);
    }

    public KvMapException(Throwable cause) {
        super(cause);
    }

}
