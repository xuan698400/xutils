package com.xuan.hisql.service.model;

/**
 * @author xuan
 * @since 2022/8/23
 */
public enum ConnectStatus {

    //
    ON("ON", "连通状态"),
    OFF("OFF", "未连通状态");

    private String code;

    private String msg;

    ConnectStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ConnectStatus of(String code) {
        for (ConnectStatus e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
