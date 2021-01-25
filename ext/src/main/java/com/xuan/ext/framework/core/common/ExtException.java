package com.xuan.ext.framework.core.common;

/**
 * @author xuan
 * @since 2021/1/24
 */
public class ExtException extends RuntimeException {
    public ExtException(String message) {
        super(message);
    }

    public ExtException(String message, Throwable cause) {
        super(message, cause);
        
    }
}
