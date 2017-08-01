package com.xuan.xutils.http;

/**
 * HTT访问通用接口
 * 
 * @author xuan
 */
public interface HttpClient {
	/**
	 * POST请求，Json放到请求体里面
	 * 
	 * @param bpRequest
	 * @return
	 */
	HttpResponse postJson(HttpRequest bpRequest);

	/**
	 * POST请求，普通参数的方式提交
	 * 
	 * @param bpRequest
	 * @return
	 */
	HttpResponse post(HttpRequest bpRequest);

	/**
	 * GET请求
	 * 
	 * @param bpRequest
	 * @return
	 */
	HttpResponse get(HttpRequest bpRequest);

	/**
	 * 下载，用的是GET请求
	 *
	 * @param bpRequest
	 * @return
	 */
	HttpResponse getDowload(HttpRequest bpRequest);

	/**
	 * 下载，用的是POST请求
	 *
	 * @param bpRequest
	 * @return
	 */
	HttpResponse postDowload(HttpRequest bpRequest);

	/**
	 * 模拟表单上传文件
	 *
	 * @param bpRequest
	 * @return
	 */
	HttpResponse upload(HttpRequest bpRequest);

}
