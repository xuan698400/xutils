package com.xuan.mix.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * IO流操作工具类
 * Created by xuan on 2019/1/29.
 */
public abstract class IOUtils {

    private static final int EOF                 = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 关闭流，隐藏关闭时出现的异常
     *
     * @param closeable 可关闭接口
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
     * @throws IOException IO异常
     */
    public static void write(String data, OutputStream output, String encoding) throws IOException {
        if (data != null) {
            output.write(data.getBytes(Charsets.toCharset(encoding)));
        }
    }

    /**
     * 把字节流按指定编码组成字符串
     *
     * @param input    输入流
     * @param encoding 编码
     * @return 结果字符串
     * @throws IOException IO异常
     */
    public static String toString(InputStream input, String encoding) throws IOException {
        // 把字节流转成字符流
        InputStreamReader in = new InputStreamReader(input, Charsets.toCharset(encoding));

        int n;
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        while (EOF != (n = in.read(buffer))) {
            builder.append(buffer, 0, n);
        }

        return builder.toString();
    }

    /**
     * 从流中准确的读出指定字节，读取后会严格用读取后的字节数和传入的longSize进行比较，只有相等了才返回数据，否则抛出异常
     * 如果流中的字节大于指定longSize的长度，只够读取出流中前longSize长度的字节数组
     *
     * @param input    输入流
     * @param longSize 该输入流的期望长度
     * @return 读出的字节数组
     * @throws IOException IO异常
     */
    public static byte[] toByteArray(InputStream input, long longSize) throws IOException {
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
            throw new IOException("实际读取的流的长度和期望的长度不一致，实际读取长度：" + offset + ", 期望长度: " + size);
        }

        return data;
    }

}
