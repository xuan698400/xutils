package com.xuan.common.exception;

/**
 * @author xuan
 * @since 2020/2/28
 */
public interface BizExceptionCode {

    /**
     * 错误码Code
     *
     * @return code
     */
    String getCode();

    /**
     * 错误码提示
     *
     * @param params 替换参数
     * @return msg
     */
    String getMsg(Object... params);
}
