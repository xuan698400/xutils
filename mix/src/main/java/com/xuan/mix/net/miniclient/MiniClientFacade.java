package com.xuan.mix.net.miniclient;

import java.io.File;
import java.util.Map;

import com.xuan.mix.net.miniclient.impl.HttpClientUrlConnectionImpl;

/**
 * HTTP快捷使用工具类
 *
 * @author xuan
 * @date 2019/5/19
 */
public class MiniClientFacade {
    public static final boolean DEBUG = false;

    private static final MiniClient client = new HttpClientUrlConnectionImpl();

    public static MiniClientResponse postJson(String url, String bodyJson) {
        MiniClientRequest request = new MiniClientRequest();
        request.setUrl(url);
        request.putBodyJson(bodyJson);

        printLog(request);
        return client.postJson(request);
    }

    public static MiniClientResponse post(String url, Map<String, String> paramsMap) {
        MiniClientRequest request = new MiniClientRequest();
        request.setUrl(url);
        if (null != paramsMap) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
        }

        printLog(request);
        return client.post(request);
    }

    public static MiniClientResponse get(String url, Map<String, String> paramsMap) {
        MiniClientRequest request = new MiniClientRequest();
        request.setUrl(url);
        if (null != paramsMap) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
        }

        printLog(request);
        return client.get(request);
    }

    public static MiniClientResponse upload(String url,
        Map<String, File> fileMap, Map<String, String> paramMap) {

        MiniClientRequest request = new MiniClientRequest();
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

    public static MiniClientResponse getDownload(String url,
        Map<String, String> paramsMap, String saveFileName,
        MiniClientDownloadListener downloadListener) {

        MiniClientRequest request = new MiniClientRequest();
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

    public static MiniClientResponse postDownload(String url,
        Map<String, String> paramsMap, String saveFileName,
        MiniClientDownloadListener downloadListener) {

        MiniClientRequest request = new MiniClientRequest();
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

    private static void printLog(MiniClientRequest request) {
        if (DEBUG) {
            System.out.println(request.toString());
        }
    }

}
