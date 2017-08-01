package com.xuan.xutils.http;

import com.xuan.xutils.http.listener.HttpDownloadListener;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Http请求对象
 *
 * @author xuan
 */
public class HttpRequest {
    /**
     * 请求地址. 例如:http://xuanner.com
     */
    private String              mUrl;
    /**
     * 普通参数
     */
    private Map<String, String> mParamMap;
    /**
     * 文件参数
     */
    private Map<String, File>   mFileParamMap;
    /**
     * 头部参数
     */
    private Map<String, String> mHeaderMap;
    /**
     *  用请求体Json方式提交的Json内容
     */
    private String              mBodyJson;
    /**
     * 提交或者获取的编码方式
     */
    private String mEncode            = "utf-8";
    /**
     * 连接超时
     */
    private int    mConnectionTimeout = 1000 * 30;
    /**
     * 读取超时
     */
    private int    mReadTimeout       = 1000 * 30;
    /**
     * 结果返回回调,只有下载文件时会被调用
     */
    private HttpDownloadListener mDownloadListener;
    /**
     * 下载时文件存放路径
     */
    private String               mDownloadFileName;

    public HttpRequest() {
        init();
    }

    public int getConnectionTimeout() {
        return mConnectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.mConnectionTimeout = connectionTimeout;
    }

    public String getDownloadFileName() {
        return mDownloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.mDownloadFileName = downloadFileName;
    }

    public String getEncode() {
        return mEncode;
    }

    public void setEncode(String encode) {
        this.mEncode = encode;
    }

    public int getReadTimeout() {
        return mReadTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.mReadTimeout = readTimeout;
    }

    public HttpDownloadListener getDownloadListener() {
        return mDownloadListener;
    }

    public void setDownloadListener(HttpDownloadListener downloadListener) {
        this.mDownloadListener = downloadListener;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    /**
     * 放入请求体Json串
     *
     * @param bodyJson
     */
    public void putBodyJson(String bodyJson) {
        if (null != bodyJson) {
            this.mBodyJson = bodyJson;
        }
    }

    /**
     * 添加普通参数
     *
     * @param key
     * @param value
     */
    public void putParam(String key, String value) {
        if (key != null && value != null) {
            mParamMap.put(key, value);
        }
    }

    /**
     * 添加文件参数
     *
     * @param key
     * @param file
     */
    public void putFile(String key, File file) {
        mFileParamMap.put(key, file);
    }

    /**
     * 添加头部
     *
     * @param key
     * @param value
     */
    public void putHeader(String key, String value) {
        mHeaderMap.put(key, value);
    }

    /**
     * 删除普通参数
     *
     * @param key
     */
    public void removeParam(String key) {
        mParamMap.remove(key);
    }

    /**
     * 删除文件参数
     *
     * @param key
     */
    public void removeFile(String key) {
        mFileParamMap.remove(key);
    }

    /**
     * 删除头部
     *
     * @param key
     */
    public void removeHeader(String key) {
        mHeaderMap.remove(key);
    }

    /**
     * 返回参数的拼接
     *
     * @return
     */
    public String getParamsStr() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : mParamMap.entrySet()) {
            sb.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue())).append("&");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 获取GET参数串
     *
     * @return
     */
    public String getGetUrl() {
        if (!getUrl().contains("?")) {
            return getUrl() + "?" + getParamsStr();
        } else {
            return getUrl() + "&" + getParamsStr();
        }
    }

    public Map<String, String> getParamMap() {
        return mParamMap;
    }

    public Map<String, File> getFileParamMap() {
        return mFileParamMap;
    }

    public Map<String, String> getHeaderMap() {
        return mHeaderMap;
    }

    public String getBodyJson() {
        return mBodyJson;
    }

    // 初始化MAP
    private void init() {
        mParamMap = new ConcurrentHashMap<String, String>();
        mFileParamMap = new ConcurrentHashMap<String, File>();
        mHeaderMap = new ConcurrentHashMap<String, String>();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : mParamMap
                .entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        for (Entry<String, File> entry : mFileParamMap
                .entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }

            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }

        if (!getUrl().contains("?")) {
            return getUrl() + "?" + result.toString();
        } else {
            return getUrl() + "&" + result.toString();
        }
    }

}
