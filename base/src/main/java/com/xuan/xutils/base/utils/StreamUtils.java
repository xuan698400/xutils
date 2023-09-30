package com.xuan.xutils.base.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 各种流读取/写入常用工具类
 *
 * @author xuan
 * @since 2023/8/26
 */
public class StreamUtils {

    /**
     * 读流时结束符号
     */
    private static final int EOF = -1;

    /**
     * 读流时缓冲大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 安静的关闭流
     *
     * @param closeable 可关闭的流
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    /**
     * 从InputStream流里面读取String
     *
     * @param inputStream 输入流
     * @param charset     编码
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String readToString(InputStream inputStream, Charset charset) throws IOException {
        try {
            InputStreamReader in = new InputStreamReader(inputStream, charset);
            int n;
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            while (EOF != (n = in.read(buffer))) {
                builder.append(buffer, 0, n);
            }
            return builder.toString();
        } finally {
            closeQuietly(inputStream);
        }
    }

}
