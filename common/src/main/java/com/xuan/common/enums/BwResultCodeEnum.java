package com.xuan.common.enums;

import com.xuan.common.model.BwResultCode;

/**
 * 错误码枚举
 *
 * @author xuan
 * @since 2020/1/10
 */
public enum BwResultCodeEnum implements BwResultCode {

    //
    SUCCESS("success", "成功"),
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "程序未知异常"),
    PARAM_NULL("PARAM_NULL", "参数不能NULL"),
    PARAM_EMPTY("PARAM_EMPTY", "参数不能空"),
    PARAM_INVALID("PARAM_INVALID", "参数不合法"),
    ;

    private String code;
    private String msg;

    BwResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public static BwResultCodeEnum codeOf(String code) {
        if (null == code) {
            return null;
        }

        for (BwResultCodeEnum e : BwResultCodeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
