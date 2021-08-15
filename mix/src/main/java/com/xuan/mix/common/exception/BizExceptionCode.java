package com.xuan.mix.common.exception;

/**
 * @author xuan
 * @since 2020/2/28
 */
public interface BizExceptionCode {

    String getCode();

    String getMsg(Object... args);
}
