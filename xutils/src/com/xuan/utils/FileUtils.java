package com.xuan.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 文件工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:52:05 $
 */
public abstract class FileUtils {

    /**
     * 文件大小单位：1KB。
     */
    public static final long ONE_KB = 1024;

    /**
     * 文件大小单位：1MB。
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * 文件大小单位：1GB。
     */
    public static final long ONE_GB = ONE_KB * ONE_MB;

    private static final int BUFFER_SIZE = 4096;

    /**
     * 递归取得某个目录下所有的文件
     * 
     * @param path
     *            目录
     * @return 文件List
     */
    public static List<File> getNestedFiles(String path) {
        File directory = new File(path);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Nonexistent directory[" + path + "]");
        }

        return new Recursiver().getFileList(directory);
    }

    /**
     * 把字符串写到文件中
     * 
     * @param path
     *            文件路径
     * @param str
     *            字符串
     * @param append
     *            是否追加，否的话会覆盖原来的内容
     */
    public static void writeString(String path, String str, boolean append) {
        FileWriter out = null;
        try {
            out = new FileWriter(path, append);
            out.write(str);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not write String[" + path + "]", e);
        }
        finally {
            close(out);
        }
    }

    /**
     * 从文件中读取字符串，使用默认字符集
     * 
     * @param path
     *            文件路径
     * @return 文件内容的字符串
     */
    public static String readString(String path) {
        FileInputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return new String(out.toByteArray());
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read String[" + path + "]", e);
        }
        finally {
            close(in);
            close(out);
        }
    }

    /**
     * 从输入流中读取字符串，使用默认字符集
     * 
     * @param in
     *            输入流
     * @return 流内容的字符串
     */
    public static String readString(InputStream in) {
        ByteArrayOutputStream out = null;

        try {
            out = new ByteArrayOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return new String(out.toByteArray());
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read stream", e);
        }
        finally {
            close(in);
            close(out);
        }
    }

    /**
     * 读取指定路径的Properties文件
     * 
     * @param path
     *            路径
     * @return Properties对象
     */
    public static Properties readProperties(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        Properties properties = new Properties();
        InputStream in = null;

        try {
            in = new FileInputStream(file);
            properties.load(in);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read Properties[" + path + "]", e);
        }
        finally {
            close(in);
        }

        return properties;
    }

    /**
     * 从输入流读取Properties对象
     * 
     * @param in
     *            输入流
     * @return Properties对象
     */
    public static Properties readProperties(InputStream in) {
        Properties properties = new Properties();

        try {
            properties.load(in);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read Properties", e);
        }
        finally {
            close(in);
        }

        return properties;
    }

    /**
     * 把Properties对象写到指定路径的文件里
     * 
     * @param path
     *            路进
     * @param properties
     *            Properties对象
     */
    public static void writeProperties(String path, Properties properties) {
        OutputStream out = null;

        try {
            out = new FileOutputStream(path);
            properties.store(out, null);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not write Properties[" + path + "]", e);
        }
        finally {
            close(out);
        }
    }

    /**
     * 关闭输入流
     * 
     * @param in
     *            输入流
     */
    public static void close(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        }
        catch (IOException e) {
        }
    }

    /**
     * 关闭输出流
     * 
     * @param out
     *            输出流
     */
    public static void close(OutputStream out) {
        try {
            if (out != null) {
                out.close();
            }
        }
        catch (IOException e) {
        }
    }

    /**
     * 关闭 Reader
     * 
     * @param in
     *            Reader
     */
    public static void close(Reader in) {
        try {
            if (in != null) {
                in.close();
            }
        }
        catch (IOException e) {
        }
    }

    /**
     * 关闭 Writer
     * 
     * @param out
     *            Writer
     */
    public static void close(Writer out) {
        try {
            if (out != null) {
                out.close();
            }
        }
        catch (IOException e) {
        }
    }

    /**
     * 关闭 Closeable 对象。
     * 
     * @param closeable
     *            Closeable 对象
     */
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (IOException e) {
        }
    }

    /**
     * 取得文件的后缀名。
     * 
     * @param fileName
     *            文件名
     * @return 后缀名
     */
    public static String getExtension(String fileName) {
        if (Validators.isEmpty(fileName)) {
            return null;
        }

        int pointIndex = fileName.lastIndexOf(".");
        return pointIndex > 0 && pointIndex < fileName.length() ? fileName.substring(pointIndex + 1).toLowerCase()
                : null;
    }

    /**
     * 复制给定的数组中的内容到输出流中。
     * 
     * @param in
     *            需要复制的数组
     * @param out
     *            复制到的输出流
     * @throws IOException
     *             当发生 I/O 异常时抛出
     */
    public static void copy(byte[] in, OutputStream out) throws IOException {
        try {
            out.write(in);
        }
        finally {
            try {
                out.close();
            }
            catch (IOException ex) {
            }
        }
    }

    /**
     * 将以 byte 为单位的文件大小转换为一个可读性更好的文件大小，最终结果精确到一位小数。
     * 
     * @param size
     *            以 byte 为单位的文件大小
     * @return 更具可读性的文件大小（包括单位：GB、MB、KB、B），例如：102 B、1.5 KB、23.8 MB、34.2 GB
     */
    public static String byteCountToDisplaySize(long size) {
        String displaySize;

        if (size / ONE_GB > 0) {
            displaySize = MathUtils.div(size * 1.0, ONE_GB, 1) + " GB";
        }
        else if (size / ONE_MB > 0) {
            displaySize = MathUtils.div(size * 1.0, ONE_MB, 1) + " MB";
        }
        else if (size / ONE_KB > 0) {
            displaySize = MathUtils.div(size * 1.0, ONE_KB, 1) + " KB";
        }
        else {
            displaySize = String.valueOf(size) + " B";
        }

        return displaySize;
    }

    private static class Recursiver {

        private static ArrayList<File> files = new ArrayList<File>();

        public List<File> getFileList(File file) {
            File children[] = file.listFiles();

            for (int i = 0; i < children.length; i++) {
                if (children[i].isDirectory()) {
                    new Recursiver().getFileList(children[i]);
                }
                else {
                    files.add(children[i]);
                }
            }

            return files;
        }
    }

}
