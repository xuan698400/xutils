package com.xuan.mix.biz.account;

/**
 * @author xuan
 * @since 2022/10/18
 */
public class AccountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public static final String CODE_PARAM_INVALID = "PARAM_INVALID";
    public static final String CODE_DO_LOGIN_FAIL = "DO_LOGIN_FAIL";
    public static final String CODE_CHECK_LOGIN_FAIL = "CHECK_LOGIN_FAIL";

    private String code;

    public AccountException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public AccountException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
