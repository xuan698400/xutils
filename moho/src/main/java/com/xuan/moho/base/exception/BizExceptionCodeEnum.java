package com.xuan.moho.base.exception;

/**
 * @author xuan
 * @since 2020/1/10
 */
public enum BizExceptionCodeEnum implements BizExceptionCode {

    //
    SUCCESS("SUCCESS", "成功"),
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "%s"),
    PARAM_NULL("PARAM_NULL", "%s"),
    PARAM_EMPTY("PARAM_EMPTY", "%s"),
    PARAM_INVALID("PARAM_INVALID", "%s"),
    BIZ_EXCEPTION("BIZ_EXCEPTION", "%s"),
    DB_EXCEPTION("DB_EXCEPTION", "%s"),
    RPC_EXCEPTION("RPC_EXCEPTION", "%s"),
    ;

    private String code;
    private String msg;

    BizExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg(Object... args) {
        return String.format(msg, args);
    }

    public static BizExceptionCodeEnum of(String code) {
        if (null == code) {
            return null;
        }
        for (BizExceptionCodeEnum e : BizExceptionCodeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
