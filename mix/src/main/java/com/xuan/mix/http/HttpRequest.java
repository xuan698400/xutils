package com.xuan.mix.http;

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
    private final static int DEFAULT_CONNECTIONTIMEOUT = 1000 * 30;
    private final static int DEFAULT_READTIMEOUT = 1000 * 30;
    private final static String DEFAULT_ENCODE = "utf-8";
    private final static String URL_PARAM_DIVISION = "?";
    private final static String PARAM_PARAM_DIVISION = "&";
    private final static String EQUAL = "=";
    private final static String FILE_STR = "FILE";

    /**
     * 请求地址. 例如:http://xuanner.com
     */
    private String mUrl;
    /**
     * 普通参数
     */
    private Map<String, String> mParamMap;
    /**
     * 文件参数
     */
    private Map<String, File> mFileParamMap;
    /**
     * 头部参数
     */
    private Map<String, String> mHeaderMap;
    /**
     * 用请求体Json方式提交的Json内容
     */
    private String mBodyJson;
    /**
     * 提交或者获取的编码方式
     */
    private String mEncode = DEFAULT_ENCODE;
    /**
     * 连接超时
     */
    private int mConnectionTimeout = DEFAULT_CONNECTIONTIMEOUT;
    /**
     * 读取超时
     */
    private int mReadTimeout = DEFAULT_READTIMEOUT;
    /**
     * 结果返回回调,只有下载文件时会被调用
     */
    private HttpDownloadListener mDownloadListener;
    /**
     * 下载时文件存放路径
     */
    private String mDownloadFileName;

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
     * @param bodyJson JSON格式
     */
    public void putBodyJson(String bodyJson) {
        if (null != bodyJson) {
            this.mBodyJson = bodyJson;
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
            mParamMap.put(key, value);
        }
    }

    /**
     * 添加文件参数
     *
     * @param key  参数key
     * @param file 文件对象
     */
    public void putFile(String key, File file) {
        mFileParamMap.put(key, file);
    }

    /**
     * 添加头部
     *
     * @param key   参数key
     * @param value 参数值
     */
    public void putHeader(String key, String value) {
        mHeaderMap.put(key, value);
    }

    /**
     * 删除普通参数
     *
     * @param key 参数key
     */
    public void removeParam(String key) {
        mParamMap.remove(key);
    }

    /**
     * 删除文件参数
     *
     * @param key 参数key
     */
    public void removeFile(String key) {
        mFileParamMap.remove(key);
    }

    /**
     * 删除头部
     *
     * @param key 参数key
     */
    public void removeHeader(String key) {
        mHeaderMap.remove(key);
    }

    /**
     * 获取参数串
     * 例如：a=b&c=d
     *
     * @return 字符串
     */
    public String getParamsStr() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : mParamMap.entrySet()) {
            String encodeValue = null;
            try {
                encodeValue = URLEncoder.encode(entry.getValue(), DEFAULT_ENCODE);
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

    private void init() {
        mParamMap = new ConcurrentHashMap<>(16);
        mFileParamMap = new ConcurrentHashMap<>(16);
        mHeaderMap = new ConcurrentHashMap<>(16);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : mParamMap
            .entrySet()) {
            if (result.length() > 0) {
                result.append(PARAM_PARAM_DIVISION);
            }

            result.append(entry.getKey());
            result.append(EQUAL);
            result.append(entry.getValue());
        }

        for (Entry<String, File> entry : mFileParamMap
            .entrySet()) {
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
