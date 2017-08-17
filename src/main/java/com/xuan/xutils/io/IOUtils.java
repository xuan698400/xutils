package com.xuan.xutils.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * IO流操作工具类
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-9-4 下午7:22:40 $
 */
public abstract class IOUtils {
    private static final int EOF                 = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 默默的关闭可关闭流
     *
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    /**
     * 把数据以指定编码写入输出流中
     *
     * @param data     字符串数据
     * @param output   输出流
     * @param encoding 编码
     * @throws IOException
     */
    public static void write(String data, OutputStream output, String encoding)
            throws IOException {
        if (data != null) {
            output.write(data.getBytes(Charsets.toCharset(encoding)));
        }
    }

    /**
     * 把字节流按指定编码组成字符串
     *
     * @param input    输入流
     * @param encoding 编码
     * @return
     * @throws IOException
     */
    public static String toString(InputStream input, String encoding)
            throws IOException {
        // 把字节流转成字符流
        InputStreamReader in = new InputStreamReader(input, Charsets.toCharset(encoding));

        int n = 0;
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        while (EOF != (n = in.read(buffer))) {
            if (null != buffer) {
                builder.append(buffer, 0, n);
            }
        }

        return builder.toString();
    }

    /**
     * 从流中准确的读出指定字节，读取后会严格用读取后的字节数和传入的longSize进行比较，只要相等了才返回数据，否则抛出异常
     *
     * @param input    输入流
     * @param longSize 该输入流的期望长度
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream input, long longSize)
            throws IOException {
        if (longSize > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("流的期望长度不能超过int能表示的范围，当前长度： " + longSize);
        }

        int size = (int) longSize;// 转成int

        if (size < 0) {
            throw new IllegalArgumentException("流的期望长度必须大于等于0，当前长度： " + size);
        }

        if (size == 0) {
            return new byte[0];
        }

        byte[] data = new byte[size];
        int offset = 0;
        int readed;

        while (offset < size && (readed = input.read(data, offset, size - offset)) != EOF) {
            offset += readed;
        }

        if (offset != size) {
            throw new IOException("实际读取的流的长度和期望的长度不一致，实际读取长度：" + offset
                    + ", 期望长度: " + size);
        }

        return data;
    }

}
