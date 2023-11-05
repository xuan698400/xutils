package com.xuan.crudboy.exception;

/**
 * 业务自定义异常，构建异常时，必须使用继承了BizExceptionCode的枚举，目的是为了规范异常的异常码
 *
 * @author xuan
 * @since 2021/7/12
 */
public class CbException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private String code;

    public CbException(CbExceptionCode code, Object... params) {
        super(code.getMsg(params));
        this.code = String.format("%s_%s", code.getCode(), code.getSubCode());
    }

    public CbException(CbExceptionCode code, Throwable e, Object... params) {
        super(code.getMsg(params), e);
        this.code = String.format("%s_%s", code.getCode(), code.getSubCode());
    }

    public String getCode() {
        return code;
    }

}
