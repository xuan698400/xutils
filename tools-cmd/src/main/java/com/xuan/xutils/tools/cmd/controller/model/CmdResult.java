package com.xuan.xutils.tools.cmd.controller.model;

/**
 * 通用返回结果
 *
 * @author xuan
 * @since 2023/8/24
 */
public class CmdResult<T> {

    /**
     * true/false
     */
    private boolean success;

    /**
     * 返回
     */
    private T data;

    /**
     * 提示信息
     */
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> CmdResult<T> success(T data) {
        CmdResult<T> result = new CmdResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

}
