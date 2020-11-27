package com.xuan.rbac.common;

/**
 * @author xuan
 * @since 2020/3/14
 */
public enum ErrorCodeEnum {
    //
    UNKNOW_EXCEPTION("UNKNOW_EXCEPTION", "未知异常[%s]"),
    USERNAME_REPEAT("USERNAME_REPEAT", "[%s]用户名重复"),
    PARAM_EMPTY("PARAM_EMPTY", "[%s]参数不能为空"),
    PARAM_NULL("PARAM_NULL", "[%s]参数不能为NULL"),
    PARAM_INVALID("PARAM_INVALID", "[%s]参数不合法"),
    ;

    private String code;
    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg(Object... args) {
        return String.format(msg, args);
    }

    public static ErrorCodeEnum codeOf(String code) {
        if (null == code) {
            return null;
        }

        for (ErrorCodeEnum e : ErrorCodeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
