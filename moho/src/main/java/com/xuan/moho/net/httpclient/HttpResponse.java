package com.xuan.moho.net.httpclient;

import java.io.File;

/**
 * Http响应对象
 *
 * @author xuan
 * @date 2019/5/19
 */
public class HttpResponse {
    /**
     * 成功的状态码
     */
    public static final int STATUS_CODE_SUCCESS = 200;
    /**
     * 失败的状态码
     */
    public static final int STATUS_CODE_FAIL = -1;
    /**
     * 返回状态码；成功200
     */
    private int statusCode;
    /**
     * 返回状态短语
     */
    private String reasonPhrase;
    /**
     * 返回结果：当返回是字符串时才有
     */
    private String resultStr;
    /**
     * 返回结果：当返回是文件时才有
     */
    private File resultFile;

    public HttpResponse() {
        this(STATUS_CODE_FAIL, null);
    }

    public HttpResponse(int statusCode) {
        this(statusCode, null);
    }

    public HttpResponse(int statusCode, String reasonPhrase) {
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    public boolean isStatusOk() {
        return STATUS_CODE_SUCCESS == statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public File getResultFile() {
        return resultFile;
    }

    public void setResultFile(File resultFile) {
        this.resultFile = resultFile;
    }

    @Override
    public String toString() {
        return "statusCode[" + statusCode + "]reasonPhrase[" + reasonPhrase + "]resultStr[" + resultStr + "]";
    }

}
