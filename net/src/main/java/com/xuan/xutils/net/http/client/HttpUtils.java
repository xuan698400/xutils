package com.xuan.xutils.net.http.client;

import java.io.File;
import java.util.Map;

import com.xuan.xutils.net.http.client.core.HttpClientUrlConnectionImpl;

/**
 * HTTP快捷使用工具类
 *
 * @author xuan
 * @date 2019/5/19
 */
public class HttpUtils {
    public static final boolean DEBUG = false;

    private static final HttpClient client = new HttpClientUrlConnectionImpl();

    public static HttpResponse postJson(String url, String bodyJson) {
        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.putBodyJson(bodyJson);

        printLog(request);
        return client.postJson(request);
    }

    public static HttpResponse post(String url, Map<String, String> paramsMap) {
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

    public static HttpResponse get(String url, Map<String, String> paramsMap) {
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

    public static HttpResponse upload(String url,
        Map<String, File> fileMap, Map<String, String> paramMap) {

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

    public static HttpResponse getDownload(String url,
        Map<String, String> paramsMap, String saveFileName,
        HttpDownloadListener downloadListener) {

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

    public static HttpResponse postDownload(String url,
        Map<String, String> paramsMap, String saveFileName,
        HttpDownloadListener downloadListener) {

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

    private static void printLog(HttpRequest request) {
        if (DEBUG) {
            System.out.println(request.toString());
        }
    }

}
