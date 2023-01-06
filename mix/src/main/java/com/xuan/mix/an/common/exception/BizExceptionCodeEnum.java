package com.xuan.mix.an.common.exception;

/**
 * @author xuan
 * @since 2020/1/10
 */
public enum BizExceptionCodeEnum implements BizExceptionCode {

    //
    SUCCESS("SUCCESS", "成功"),
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "程序未知异常. msg:%s"),
    PARAM_NULL("PARAM_NULL", "[%s]参数不能NULL"),
    PARAM_EMPTY("PARAM_EMPTY", "[%s]参数不能空"),
    PARAM_INVALID("PARAM_INVALID", "[%s]参数不合法"),
    BIZ_ERROR("BIZ_ERROR", "[%s]"),
    DB_ERROR("DB_ERROR", "[%s]"),
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
