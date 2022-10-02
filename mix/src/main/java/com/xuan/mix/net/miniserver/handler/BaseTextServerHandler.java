package com.xuan.mix.net.miniserver.handler;

import java.util.Map;

import com.xuan.mix.net.miniserver.MiniServerHandler;
import com.xuan.mix.net.miniserver.MiniServerResponse;

/**
 * @author xuan
 * @since 2022/6/28
 */
public abstract class BaseTextServerHandler implements MiniServerHandler {

    @Override
    public MiniServerResponse handle(String path, Map<String, String> params) {
        MiniServerResponse response = new MiniServerResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setContent(doHandle(path, params));
        return response;
    }

    protected abstract String doHandle(String path, Map<String, String> params);
}
