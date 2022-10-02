package com.xuan.mix.net.miniclient;

/**
 * HTT访问通用接口
 *
 * @author xuan
 * @date 2019/5/19
 */
public interface MiniClient {
    /**
     * POST请求，Json放到请求体里面
     *
     * @param request MiniClientRequest
     * @return MiniClientResponse
     */
    MiniClientResponse postJson(MiniClientRequest request);

    /**
     * POST请求，普通参数的方式提交
     *
     * @param request MiniClientRequest
     * @return MiniClientResponse
     */
    MiniClientResponse post(MiniClientRequest request);

    /**
     * GET请求
     *
     * @param request MiniClientRequest
     * @return MiniClientResponse
     */
    MiniClientResponse get(MiniClientRequest request);

    /**
     * 下载，用的是GET请求
     *
     * @param request MiniClientRequest
     * @return MiniClientResponse
     */
    MiniClientResponse getDowload(MiniClientRequest request);

    /**
     * 下载，用的是POST请求
     *
     * @param request MiniClientRequest
     * @return MiniClientResponse
     */
    MiniClientResponse postDowload(MiniClientRequest request);

    /**
     * 模拟表单上传文件
     *
     * @param request MiniClientRequest
     * @return MiniClientResponse
     */
    MiniClientResponse upload(MiniClientRequest request);

}
