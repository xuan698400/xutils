package com.xuan.moho.tools.sequence;

/**
 * @author xuan
 * @date 2018/1/10
 */
public class SequenceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SequenceException(String message) {
        super(message);
    }

    public SequenceException(Throwable cause) {
        super(cause);
    }

}
