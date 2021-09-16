package com.xuan.mix.bt.mapping;

/**
 * @author xuan
 * @since 2021/9/15
 */
public class MappingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable e) {
        super(message, e);
    }

    public MappingException(Throwable e) {
        super(e);
    }

}
