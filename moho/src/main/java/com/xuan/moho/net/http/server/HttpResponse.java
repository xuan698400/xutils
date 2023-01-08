package com.xuan.moho.net.http.server;

/**
 * HTTP响应
 *
 * @author xuan
 * @since 2022/6/28
 */
public class HttpResponse {

    private String contentType = "text/plain;charset=utf-8";

    private String content;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
