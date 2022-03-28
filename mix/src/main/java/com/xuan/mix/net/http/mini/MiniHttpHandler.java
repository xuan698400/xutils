package com.xuan.mix.net.http.mini;

import java.util.Map;

/**
 * 请求处理
 *
 * @author xuan
 * @since 2020/10/16
 */
public interface MiniHttpHandler {

    /**
     * 处理请求
     *
     * @param path   访问路径
     * @param params get参数
     * @return 返回结果
     */
    String handle(String path, Map<String, String> params);
}
