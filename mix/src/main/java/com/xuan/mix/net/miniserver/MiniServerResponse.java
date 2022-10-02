package com.xuan.mix.net.miniserver;

/**
 * @author xuan
 * @since 2022/6/28
 */
public class MiniServerResponse {

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
