package com.xuan.moho.net.httpserver;

import java.util.Map;

/**
 * HTTP请求
 *
 * @author xuan
 * @since 2023/1/6
 */
public class HttpRequest {

    /**
     * 访问路径，例如：http://localhost:8888/ddd/xxxxxx.html这里path=ddd/xxxxxx.html
     */
    private String path;

    /**
     * 访问KV参数
     */
    private Map<String, String> params;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

}
