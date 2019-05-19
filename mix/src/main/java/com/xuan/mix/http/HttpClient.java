package com.xuan.mix.http;

/**
 * HTT访问通用接口
 *
 * @author xuan
 * @date 2019/5/19
 */
public interface HttpClient {
    /**
     * POST请求，Json放到请求体里面
     *
     * @param request HttpRequest
     * @return HttpResponse
     */
    HttpResponse postJson(HttpRequest request);

    /**
     * POST请求，普通参数的方式提交
     *
     * @param request HttpRequest
     * @return HttpResponse
     */
    HttpResponse post(HttpRequest request);

    /**
     * GET请求
     *
     * @param request HttpRequest
     * @return HttpResponse
     */
    HttpResponse get(HttpRequest request);

    /**
     * 下载，用的是GET请求
     *
     * @param request HttpRequest
     * @return HttpResponse
     */
    HttpResponse getDowload(HttpRequest request);

    /**
     * 下载，用的是POST请求
     *
     * @param request HttpRequest
     * @return HttpResponse
     */
    HttpResponse postDowload(HttpRequest request);

    /**
     * 模拟表单上传文件
     *
     * @param request HttpRequest
     * @return HttpResponse
     */
    HttpResponse upload(HttpRequest request);

}
