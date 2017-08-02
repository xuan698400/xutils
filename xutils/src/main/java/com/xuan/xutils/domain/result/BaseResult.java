package com.xuan.xutils.domain.result;

import com.xuan.xutils.domain.BaseDO;

import java.util.Date;

/**
 * 返回接口基类
 * <p>
 * Created by xuan on 17/8/2.
 */
public class BaseResult extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * 结果状态码,一般用来表示这次调用是否成功
     */
    private int code;

    /**
     * 时间戳
     */
    private long serverTime = new Date().getTime();

    /**
     * 当返回一种错误结果时,不光要设置message错误信息,最好再设置一个bizCode,调用方可以根据这个bizCode来做响应的业务处理
     */
    private String bizCode;

    /**
     * 成功或者失败提示信息
     */
    private String message;

    /**
     * 返回的结果对象
     */
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

}
