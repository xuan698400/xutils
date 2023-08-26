package com.xuan.xutils.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 读取resources资源文件常用工具类
 *
 * @author xuan
 * @since 2023/8/26
 */
public class ResourceUtils {

    /**
     * 从resource读取文件，并转成String
     *
     * @param resourceName 文件名
     * @param charset      编码
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String readToString(String resourceName, Charset charset) throws IOException {
        InputStream inputStream = ResourceUtils.class.getClassLoader().getResourceAsStream(resourceName);
        if (null == inputStream) {
            return null;
        }
        return StreamUtils.readToString(inputStream, charset);
    }

}
