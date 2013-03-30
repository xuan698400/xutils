package com.xuan.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * URL 工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:58:48 $
 */
public abstract class URLUtils {

    private static final char AND_SIGN = '&';
    private static final char EQUALS_SIGN = '=';
    private static final char POINT_SIGN = '.';
    private static final char QUESTION_MARK = '?';
    private static final char SEPARATOR_SIGN = '/';

    private static int BUFFER_SIZE = 1024 * 4; // 4K

    private static String charSet = "GBK";

    /**
     * 设置字母集
     * 
     * @param charSet
     */
    public static void setCharSet(String charSet) {
        URLUtils.charSet = charSet;
    }

    /**
     * 拼接URL，参数从obj中通过反射取得
     * 
     * @param url
     *            URL
     * @param obj
     *            对象
     * @param names
     *            参数名数组
     * @return 拼接后的URL
     */
    @SuppressWarnings("rawtypes")
    public static String addInnerQueryString(String url, Object obj, String[] names) {

        Class objClass = obj.getClass();

        Object[] values = new Object[names.length];
        for (int i = 0; i < names.length; i++) {
            try {
                Field field = objClass.getDeclaredField(names[i]);
                field.setAccessible(true);
                values[i] = field.get(obj);
            }
            catch (Exception e) {
                // Ignore
            }
        }

        return addQueryString(url, names, values);
    }

    /**
     * 拼接URL
     * 
     * @param url
     *            URL
     * @param queryString
     *            查询字符串，比如：id=1
     * @return 拼接后的URL
     */
    public static String addQueryString(String url, String queryString) {
        if (Validators.isEmpty(queryString)) {
            return url;
        }

        if (url.indexOf(QUESTION_MARK) == -1) {
            url = url + QUESTION_MARK + queryString;
        }
        else {
            url = url + AND_SIGN + queryString;
        }
        return url;
    }

    /**
     * 拼接URL
     * 
     * @param url
     *            URL
     * @param name
     *            参数的名称
     * @param value
     *            参数的值
     * @return 拼接后的URL
     */
    public static String addQueryString(String url, String name, Object value) {
        return addQueryString(url, new String[] { name }, new Object[] { value });
    }

    /**
     * 拼接 URL。
     * 
     * @param url
     *            URL
     * @param names
     *            参数的名称数组
     * @param values
     *            参数的值数组
     * @return 拼接后的URL
     */
    public static String addQueryString(String url, String[] names, Object[] values) {
        if (names.length != values.length) {
            throw new IllegalArgumentException("Length of array must be equal");
        }

        StringBuilder queryString = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < names.length; i++) {
            Object value = values[i];
            if (value != null) {
                if (!isFirst) {
                    queryString.append(AND_SIGN);
                }
                else {
                    isFirst = false;
                }

                if (value instanceof Object[]) {
                    Object[] array = (Object[]) value;
                    for (int j = 0; j < array.length; j++) {
                        if (j > 0) {
                            queryString.append(AND_SIGN);
                        }
                        appendParameter(queryString, names[i], array[j]);
                    }
                }
                else if (value instanceof Collection<?>) {
                    int j = 0;
                    Collection<?> clc = (Collection<?>) value;
                    Iterator<?> iterator = clc.iterator();
                    while (iterator.hasNext()) {
                        if (j++ > 0) {
                            queryString.append(AND_SIGN);
                        }
                        appendParameter(queryString, names[i], iterator.next());
                    }
                }
                else {
                    appendParameter(queryString, names[i], value);
                }
            }
        }

        return addQueryString(url, queryString.toString());
    }

    /**
     * 对 url 按照指定编码方式解码。
     * 
     * @param url
     * @param encoding
     * @return
     */
    public static String decode(String url, String encoding) {
        try {
            return URLDecoder.decode(url, encoding);
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 使 URL 成为动态 URL，如果没有问号就在最后添加问号。
     * 
     * @param url
     * @return
     */
    public static String dynamicURL(String url) {
        if (url.indexOf(QUESTION_MARK) == -1) {
            url = url + QUESTION_MARK;
        }
        return url;
    }

    /**
     * 对 url 按照指定编码方式编码。
     * 
     * @param url
     * @param encoding
     * @return
     */
    public static String encode(String url, String encoding) {
        try {
            return URLEncoder.encode(url, encoding);
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 通过servletPath取得action的名称
     * 
     * @param servletPath
     * @return
     */
    public static String getActionName(String servletPath) {
        return servletPath.substring(servletPath.lastIndexOf(SEPARATOR_SIGN) + 1, servletPath.lastIndexOf(POINT_SIGN));
    }

    /**
     * 取得url的后缀名
     * 
     * @param url
     * @return
     */
    public static String getExtension(String url) {
        int pointIndex = url.indexOf(POINT_SIGN);

        if (pointIndex == -1) {
            return null;
        }

        int interrogationIndex = url.indexOf(QUESTION_MARK);

        return interrogationIndex == -1 ? url.substring(pointIndex + 1) : url.substring(pointIndex + 1,
                interrogationIndex);
    }

    /**
     * 通过servletPath获得namespace
     * 
     * @param servletPath
     * @return
     */
    public static String getNamespace(String servletPath) {
        return servletPath.substring(0, servletPath.lastIndexOf(SEPARATOR_SIGN));
    }

    /**
     * 忽略URL中的末尾的'/'符号.
     * 
     * @param url
     *            url地址字符串
     * @return 忽略末尾'/'符号后的url地址.
     */
    public static String ignoreLastRightSlash(String url) {
        if (Validators.isEmpty(url)) {
            return url;
        }

        // 末尾字符是否为'/', 若是则去除
        if (url.charAt(url.length() - 1) == SEPARATOR_SIGN) {
            return url.substring(0, url.length() - 1);
        }

        return url;
    }

    /**
     * 访问页面URL，获得输入流
     * 
     * @param pageURL
     *            页面URL
     * @return 输入流
     * @throws IOException
     */
    public static InputStream visitContent(String pageURL) throws IOException {
        URL url = new URL(pageURL);
        HttpURLConnection client = (HttpURLConnection) url.openConnection();

        HttpURLConnection.setFollowRedirects(false);
        client.setInstanceFollowRedirects(false);

        client.connect();

        return client.getInputStream();
    }

    /**
     * 访问页面URL，获得页面内容
     * 
     * @param pageURL
     *            页面URL
     * @return 页面内容
     * @throws IOException
     */
    public static String readContent(String pageURL) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            in = visitContent(pageURL);
            out = new ByteArrayOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return new String(out.toByteArray());
        }
        finally {
            FileUtils.close(in);
            FileUtils.close(out);
        }
    }

    /**
     * 从URL中分析字符串参数，放到一个 map 里。
     * 
     * @param url
     *            URL
     * @return map，存放的都是字符串的键值对
     */
    public static Map<String, String> getParameters(String url) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        if (Validators.isEmpty(url)) {
            return parameters;
        }

        int questionMarkIndex = url.indexOf(QUESTION_MARK);
        if (questionMarkIndex == -1 || questionMarkIndex == url.length() - 1) {
            return parameters;
        }

        String queryString = url.substring(questionMarkIndex + 1);
        String[] paramArray = queryString.split(String.valueOf(AND_SIGN));

        for (int i = 0; i < paramArray.length; i++) {
            int equalsSignIndex = paramArray[i].indexOf(EQUALS_SIGN);
            if (equalsSignIndex == -1) {
                continue;
            }

            String paramName = paramArray[i].substring(0, equalsSignIndex);
            String paramValue = paramArray[i].substring(equalsSignIndex + 1);
            parameters.put(paramName, paramValue);
        }

        return parameters;
    }

    /**
     * 缩短url，把baseURL开头的部分去掉，缩短的url都是以"/"开头的
     * 
     * @param url
     * @param baseURL
     * @return
     */
    public static String shortenURL(String url, String baseURL) {
        url = StringUtils.trim(url);
        baseURL = StringUtils.trim(baseURL);

        if (baseURL != null && baseURL.endsWith(String.valueOf(SEPARATOR_SIGN))) {
            baseURL = baseURL.substring(0, baseURL.length() - 1);
        }

        return !Validators.isEmpty(url) && !Validators.isEmpty(baseURL) && url.startsWith(baseURL) ? url
                .substring(baseURL.length()) : url;
    }

    /**
     * 查询字符串后面增加参数
     * 
     * @param queryString
     *            查询字符串，比如：id=1&type=1
     * @param name
     *            参数的名称
     * @param value
     *            参数的值
     * @return 拼接后的查询字符串
     */
    private static StringBuilder appendParameter(StringBuilder queryString, String name, Object value) {
        queryString.append(name);
        queryString.append(EQUALS_SIGN);

        if (value instanceof Boolean) {
            value = ((Boolean) value).booleanValue() ? "1" : "0";
        }
        else if (value instanceof Date) {
            value = DateUtils.date2StringByDay((Date) value);
        }

        try {
            queryString.append(URLEncoder.encode(String.valueOf(value), charSet));
        }
        catch (UnsupportedEncodingException e) {
            // ignore
        }
        return queryString;
    }

}
