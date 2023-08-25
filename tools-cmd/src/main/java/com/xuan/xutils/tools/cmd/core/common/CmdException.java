package com.xuan.xutils.tools.cmd.core.common;

/**
 * @author xuan
 * @since 2023/8/25
 */
public class CmdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public CmdException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public CmdException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
}
