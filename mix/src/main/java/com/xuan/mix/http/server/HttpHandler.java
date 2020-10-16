package com.xuan.mix.http.server;

import java.util.Map;

/**
 * Http请求处理器
 *
 * @author xuan
 * @since 2020/10/16
 */
public interface HttpHandler {

    /**
     * 执行处理
     *
     * @param path   路径
     * @param params get参数
     * @return 输出结果
     */
    String handle(String path, Map<String, String> params);
}
