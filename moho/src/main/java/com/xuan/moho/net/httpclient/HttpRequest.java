package com.xuan.moho.net.httpclient;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Http请求对象
 *
 * @author xuan
 * @date 2019/5/19
 */
public class HttpRequest {
    private final static String URL_PARAM_DIVISION = "?";
    private final static String PARAM_PARAM_DIVISION = "&";
    private final static String EQUAL = "=";
    private final static String FILE_STR = "FILE";

    /**
     * 请求地址. 例如:http://xuanner.com
     */
    private String url;
    /**
     * 普通参数
     */
    private Map<String, String> paramMap;
    /**
     * 文件参数
     */
    private Map<String, File> fileParamMap;
    /**
     * 头部参数
     */
    private Map<String, String> headerMap;
    /**
     * 用请求体Json方式提交的Json内容
     */
    private String bodyJson;
    /**
     * 提交或者获取的编码方式，默认：utf-8
     */
    private String encode = "utf-8";
    /**
     * 连接超时，默认30S
     */
    private int connectionTimeout = 1000 * 30;
    /**
     * 读取超时，默认30S
     */
    private int readTimeout = 1000 * 30;
    /**
     * 结果返回回调,只有下载文件时会被调用
     */
    private HttpDownloadListener downloadListener;
    /**
     * 下载时文件存放路径
     */
    private String downloadFileName;

    public HttpRequest() {
        init();
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public HttpDownloadListener getDownloadListener() {
        return downloadListener;
    }

    public void setDownloadListener(HttpDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 放入请求体Json串
     *
     * @param bodyJson JSON格式
     */
    public void putBodyJson(String bodyJson) {
        if (null != bodyJson) {
            this.bodyJson = bodyJson;
        }
    }

    /**
     * 添加普通参数
     *
     * @param key   参数key
     * @param value 参数值
     */
    public void putParam(String key, String value) {
        if (key != null && value != null) {
            paramMap.put(key, value);
        }
    }

    /**
     * 添加文件参数
     *
     * @param key  参数key
     * @param file 文件对象
     */
    public void putFile(String key, File file) {
        fileParamMap.put(key, file);
    }

    /**
     * 添加头部
     *
     * @param key   参数key
     * @param value 参数值
     */
    public void putHeader(String key, String value) {
        headerMap.put(key, value);
    }

    /**
     * 删除普通参数
     *
     * @param key 参数key
     */
    public void removeParam(String key) {
        paramMap.remove(key);
    }

    /**
     * 删除文件参数
     *
     * @param key 参数key
     */
    public void removeFile(String key) {
        fileParamMap.remove(key);
    }

    /**
     * 删除头部
     *
     * @param key 参数key
     */
    public void removeHeader(String key) {
        headerMap.remove(key);
    }

    /**
     * 获取参数串
     * 例如：a=b&c=d
     *
     * @return 字符串
     */
    public String getParamsStr() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : paramMap.entrySet()) {
            String encodeValue = null;
            try {
                encodeValue = URLEncoder.encode(entry.getValue(), encode);
            } catch (UnsupportedEncodingException e) {
                //Ignore
            }
            if (null != encodeValue) {
                sb.append(entry.getKey()).append(EQUAL).append(encodeValue).append(
                    PARAM_PARAM_DIVISION);
            }
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 获取地址参数
     * 例如：http://xxx.com?a=b&c=d
     *
     * @return 字符串
     */
    public String getGetUrl() {
        if (!getUrl().contains(URL_PARAM_DIVISION)) {
            return getUrl() + URL_PARAM_DIVISION + getParamsStr();
        } else {
            return getUrl() + PARAM_PARAM_DIVISION + getParamsStr();
        }
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public Map<String, File> getFileParamMap() {
        return fileParamMap;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public String getBodyJson() {
        return bodyJson;
    }

    private void init() {
        paramMap = new ConcurrentHashMap<>(16);
        fileParamMap = new ConcurrentHashMap<>(16);
        headerMap = new ConcurrentHashMap<>(16);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : paramMap.entrySet()) {
            if (result.length() > 0) {
                result.append(PARAM_PARAM_DIVISION);
            }
            result.append(entry.getKey());
            result.append(EQUAL);
            result.append(entry.getValue());
        }

        for (Entry<String, File> entry : fileParamMap.entrySet()) {
            if (result.length() > 0) {
                result.append(PARAM_PARAM_DIVISION);
            }
            result.append(entry.getKey());
            result.append(EQUAL);
            result.append(FILE_STR);
        }

        if (!getUrl().contains(URL_PARAM_DIVISION)) {
            return getUrl() + URL_PARAM_DIVISION + result.toString();
        } else {
            return getUrl() + PARAM_PARAM_DIVISION + result.toString();
        }
    }

}
