package com.xuan.user.enums;

import com.xuan.common.model.BwResultCode;

/**
 * @author xuan
 * @since 2020/3/14
 */
public enum UserErrorCodeEnum implements BwResultCode {
    //
    USERNAME_REPEAT("USERNAME_REPEAT", "用户名重复"),
    ;

    private String code;
    private String msg;

    UserErrorCodeEnum(String code, String msg) {
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

    public static UserErrorCodeEnum codeOf(String code) {
        if (null == code) {
            return null;
        }

        for (UserErrorCodeEnum e : UserErrorCodeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
