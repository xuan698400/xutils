package com.xuan.xutils.http;

import com.xuan.xutils.http.impl.HttpClientUrlConnectionImpl;
import com.xuan.xutils.http.listener.HttpDownloadListener;

import java.io.File;
import java.util.Map;

/**
 * HTTP工具类，后期可以有多种实现方式
 *
 * @author xuan
 */
public abstract class HttpUtils {
    public static final boolean DEBUG = false;

    /**
     * 获取一个HttpClient示例,如果有自己的实现,可以在这里替换
     *
     * @return
     */
    public static HttpClient getHttpClient() {
        return new HttpClientUrlConnectionImpl();
    }

    /**
     * POST请求,json提交
     *
     * @param url
     * @param bodyJson 放在请求体中的字符串
     * @return
     */
    public static HttpResponse postJson(String url, String bodyJson) {
        HttpClient client = getHttpClient();

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.putBodyJson(bodyJson);

        printLog(request);
        return client.postJson(request);
    }

    /**
     * POST请求,普通参数方式提交
     *
     * @param url
     * @param paramsMap
     * @return
     */
    public static HttpResponse post(String url, Map<String, String> paramsMap) {
        HttpClient client = getHttpClient();

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        if (null != paramsMap) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
        }

        printLog(request);
        return client.post(request);
    }

    /**
     * GET请求
     *
     * @param url
     * @param paramsMap
     * @return
     */
    public static HttpResponse get(String url, Map<String, String> paramsMap) {
        HttpClient client = getHttpClient();

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        if (null != paramsMap) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
        }

        printLog(request);
        return client.get(request);
    }

    /**
     * 上传文件
     *
     * @param url
     * @param fileMap
     * @param paramMap
     * @return
     */
    public static HttpResponse upload(String url,
                                      Map<String, File> fileMap, Map<String, String> paramMap) {
        HttpClient client = getHttpClient();

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        if (null != fileMap) {
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                request.putFile(entry.getKey(), entry.getValue());
            }
        }
        if (null != paramMap) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
        }

        printLog(request);
        return client.upload(request);
    }

    /**
     * GET的方式下载
     *
     * @param url
     * @param paramsMap
     * @param saveFileName
     * @param downloadListener
     * @return
     */
    public static HttpResponse getDowload(String url,
                                          Map<String, String> paramsMap, String saveFileName,
                                          HttpDownloadListener downloadListener) {
        HttpClient client = getHttpClient();

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setDownloadFileName(saveFileName);
        request.setDownloadListener(downloadListener);
        if (null != paramsMap) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
        }

        printLog(request);
        return client.getDowload(request);
    }

    /**
     * POST的方式下载
     *
     * @param url
     * @param paramsMap
     * @param saveFileName
     * @param downloadListener
     * @return
     */
    public static HttpResponse postDowload(String url,
                                           Map<String, String> paramsMap, String saveFileName,
                                           HttpDownloadListener downloadListener) {
        HttpClient client = getHttpClient();

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setDownloadFileName(saveFileName);
        request.setDownloadListener(downloadListener);
        if (null != paramsMap) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
        }

        printLog(request);
        return client.postDowload(request);
    }

    /**
     * 打印请求日志
     *
     * @param request
     */
    private static void printLog(HttpRequest request) {
        if (DEBUG) {
            System.out.println(request.toString());
        }
    }

}
