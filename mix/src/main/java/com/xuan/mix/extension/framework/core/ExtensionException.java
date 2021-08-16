package com.xuan.mix.extension.framework.core;

/**
 * @author xuan
 * @since 2021/8/16
 */
public class ExtensionException extends RuntimeException {
    public ExtensionException(String message) {
        super(message);
    }

    public ExtensionException(String message, Throwable cause) {
        super(message, cause);
    }
}
