package com.xuan.xutils.base.file;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 文件操作工具类
 * Created by xuan on 2019/1/29.
 */
public abstract class FileUtils {

    public static final String EMPTY = "";

    public static final long ONE_KB = 1024;
    public static final long ONE_MB = ONE_KB * ONE_KB;
    public static final long ONE_GB = ONE_KB * ONE_MB;
    public static final long ONE_TB = ONE_KB * ONE_GB;
    public static final long ONE_PB = ONE_KB * ONE_TB;
    public static final long ONE_EB = ONE_KB * ONE_PB;

    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 获取文件后缀
     *
     * @param fileName 文件名，例如：xxx.jpg
     * @return 返回后缀，例如：jpg
     */
    public static String getExtension(String fileName) {
        if (null == fileName) {
            return EMPTY;
        }

        int pointIndex = fileName.lastIndexOf(".");
        return pointIndex > 0 && pointIndex < fileName.length() ? fileName.substring(
            pointIndex + 1).toLowerCase() : EMPTY;
    }

    // ////////////////////字节写入读出文件方法，一般可以用来写图片，声音等////////////////////

    public static void writeByteArrayToFile(File file, byte[] data, boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            out.write(data);
        } finally {
            closeQuietly(out);
        }
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return toByteArray(in, file.length());
        } finally {
            closeQuietly(in);
        }
    }

    // ////////////////////字符串从文件中写入读出方法////////////////////

    public static void writeStringToFile(File file, String data, String encoding, boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            if (data != null) {
                Charset charset = encoding == null ? Charset.defaultCharset() : Charset
                    .forName(encoding);
                out.write(data.getBytes(charset));
            }
        } finally {
            closeQuietly(out);
        }
    }

    public static String readFileToString(File file, String encoding) throws IOException {
        InputStream input = null;
        try {
            input = openInputStream(file);

            Charset charset = encoding == null ? Charset.defaultCharset() : Charset
                .forName(encoding);

            InputStreamReader in = new InputStreamReader(input, charset);

            int n;
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            while (EOF != (n = in.read(buffer))) {
                builder.append(buffer, 0, n);
            }

            return builder.toString();
        } finally {
            closeQuietly(input);
        }
    }

    public static String byteCountToDisplaySize(long size) {
        String displaySize;
        if (size / ONE_EB > 0) {
            displaySize = String.valueOf(size / ONE_EB) + " EB";
        } else if (size / ONE_PB > 0) {
            displaySize = String.valueOf(size / ONE_EB) + " PB";
        } else if (size / ONE_TB > 0) {
            displaySize = String.valueOf(size / ONE_TB) + " TB";
        } else if (size / ONE_GB > 0) {
            displaySize = String.valueOf(size / ONE_GB) + " GB";
        } else if (size / ONE_MB > 0) {
            displaySize = String.valueOf(size / ONE_MB) + " MB";
        } else if (size / ONE_KB > 0) {
            displaySize = String.valueOf(size / ONE_KB) + " KB";
        } else {
            displaySize = String.valueOf(size) + " bytes";
        }

        return displaySize;
    }

    // ////////////////////移动文件////////////////////

    /**
     * 剪切文件夹到文件夹
     *
     * @param src     源文件夹
     * @param destDir 目的文件夹
     * @param isCover 存在是否覆盖
     * @throws IOException IOException
     */
    public static void moveDirectoryToDirectory(File src, File destDir, boolean isCover) throws IOException {
        if (src == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination directory must not be null");
        }

        if (destDir.exists()) {
            if (isCover) {
                deleteFileOrDirectoryQuietly(destDir.getPath());
            } else {
                throw new IOException("Destination directory is exists and isCover=false");
            }
        }

        destDir.mkdirs();
        if (!destDir.exists()) {
            throw new FileNotFoundException("Destination directory '" + destDir + "' create failed]");
        }
        if (!destDir.isDirectory()) {
            throw new IOException("Destination '" + destDir + "' is not a directory");
        }

        boolean rename = src.renameTo(destDir);
        if (!rename) {
            if (destDir.getCanonicalPath().startsWith(src.getCanonicalPath())) {
                throw new IOException("Cannot move directory: " + src + " to a subdirectory of itself: " + destDir);
            }
            copyDirectoryToDirectory(src, destDir);
            deleteFileOrDirectoryQuietly(src.getPath());
            if (src.exists()) {
                throw new IOException(
                    "Failed to delete original directory '" + src + "' after copy to '" + destDir + "'");
            }
        }
    }

    /**
     * 剪切文件到文件夹
     *
     * @param srcFile 源文件
     * @param destDir 目的文件夹
     * @param isCover 存在是否覆盖
     * @throws IOException IOException
     */
    public static void moveFileToDirectory(File srcFile, File destDir, boolean isCover) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination directory must not be null");
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        if (!destDir.exists()) {
            throw new FileNotFoundException("Destination directory '" + destDir + "' create failed]");
        }

        if (!destDir.isDirectory()) {
            throw new IOException("Destination '" + destDir + "' is not a directory");
        }

        moveFile(srcFile, new File(destDir, srcFile.getName()), isCover);
    }

    /**
     * 剪切文件到文件
     *
     * @param srcFile  源文件
     * @param destFile 目的文件
     * @param isCover  存在是否覆盖
     * @throws IOException IOException
     */
    public static void moveFile(File srcFile, File destFile, boolean isCover) throws IOException {
        if (null == srcFile) {
            throw new NullPointerException("Source must not be null");
        }
        if (null == destFile) {
            throw new NullPointerException("Destination must not be null");
        }

        if (!srcFile.exists()) {
            throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
        }
        if (srcFile.isDirectory()) {
            throw new IOException("Source '" + srcFile + "' is a directory");
        }

        if (destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile + "' is a directory");
        }

        if (destFile.exists()) {
            if (isCover) {
                deleteFileOrDirectoryQuietly(destFile.getPath());
            } else {
                throw new IOException("Destination directory is exists and isCover=false");
            }
        }

        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile, destFile);
            if (!srcFile.delete()) {
                deleteFileOrDirectoryQuietly(destFile.getPath());
                throw new IOException(
                    "Failed to delete original file '" + srcFile + "' after copy to '" + destFile + "'");
            }
        }
    }

    // ////////////////////文件夹内容拷贝到指定文件夹中////////////////////

    /**
     * 拷贝文件夹到文件夹
     *
     * @param srcDir  源文件夹
     * @param destDir 目的文件夹
     * @throws IOException IOException
     */
    public static void copyDirectoryToDirectory(File srcDir, File destDir) throws IOException {
        copyDirectoryToDirectory(srcDir, new File(destDir, srcDir.getName()), null);
    }

    /**
     * 拷贝文件夹到文件夹
     *
     * @param srcDir  源文件夹
     * @param destDir 目的文件夹
     * @param filter  文件过滤器
     * @throws IOException IOException
     */
    public static void copyDirectoryToDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
        if (srcDir == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination must not be null");
        }

        if (srcDir.exists() && !srcDir.isDirectory()) {
            throw new IllegalArgumentException("Source '" + destDir + "' is not a directory");
        }
        if (destDir.exists() && !destDir.isDirectory()) {
            throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
        }

        if (!srcDir.exists()) {
            throw new FileNotFoundException("Source '" + srcDir + "' does not exist");
        }

        if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
            throw new IOException("Source '" + srcDir + "' and destination '" + destDir + "' are the same");
        }

        // Cater for destination being directory within the source directory
        // (see IO-141)
        List<String> exclusionList = null;
        if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath())) {
            File[] srcFiles = null == filter ? srcDir.listFiles() : srcDir.listFiles(filter);
            if (srcFiles != null && srcFiles.length > 0) {
                exclusionList = new ArrayList<>(srcFiles.length);
                for (File srcFile : srcFiles) {
                    File copiedFile = new File(destDir, srcFile.getName());
                    exclusionList.add(copiedFile.getCanonicalPath());
                }
            }
        }
        doCopyDirectory(srcDir, destDir, filter, true, exclusionList);
    }

    private static void doCopyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate,
        List<String> exclusionList) throws IOException {
        // recurse
        File[] srcFiles = (null == filter) ? srcDir.listFiles() : srcDir.listFiles(filter);
        if (srcFiles == null) { // null if abstract pathname does not denote a
            // directory, or if an I/O error occurs
            throw new IOException("Failed to list contents of " + srcDir);
        }
        if (destDir.exists()) {
            if (!destDir.isDirectory()) {
                throw new IOException("Destination '" + destDir + "' exists but is not a directory");
            }
        } else {
            if (!destDir.mkdirs() && !destDir.isDirectory()) {
                throw new IOException("Destination '" + destDir + "' directory cannot be created");
            }
        }
        if (!destDir.canWrite()) {
            throw new IOException("Destination '" + destDir + "' cannot be written to");
        }

        for (File srcFile : srcFiles) {
            File dstFile = new File(destDir, srcFile.getName());
            if (exclusionList == null || !exclusionList.contains(srcFile.getCanonicalPath())) {
                if (srcFile.isDirectory()) {
                    doCopyDirectory(srcFile, dstFile, filter, preserveFileDate, exclusionList);
                } else {
                    doCopyFile(srcFile, dstFile, preserveFileDate);
                }
            }
        }

        if (preserveFileDate) {
            destDir.setLastModified(srcDir.lastModified());
        }
    }

    // ////////////////////文件拷贝到指定文件夹里////////////////////

    /**
     * 拷贝文件到文件夹
     *
     * @param srcFile 源文件
     * @param destDir 目的文件夹
     * @throws IOException
     */
    public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
        copyFileToDirectory(srcFile, destDir, true);
    }

    /**
     * 拷贝文件到文件夹
     *
     * @param srcFile          源文件
     * @param destDir          目的文件夹
     * @param preserveFileDate 是否修改时间
     * @throws IOException
     */
    public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) throws IOException {
        if (null == destDir) {
            throw new NullPointerException("Destination must not be null");
        }
        if (destDir.exists() && !destDir.isDirectory()) {
            throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
        }
        File destFile = new File(destDir, srcFile.getName());
        copyFile(srcFile, destFile, preserveFileDate);
    }

    // ////////////////////文件拷贝到文件////////////////////

    /**
     * 拷贝文件到文件，默认修改目的文件为当前时间
     *
     * @param srcFile  源文件
     * @param destFile 目的文件
     * @throws IOException IO异常
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        copyFile(srcFile, destFile, true);
    }

    /**
     * 拷贝文件到文件，以下几种情况会有IO异常
     * 1. 源文件地址和目标文件地址为空
     * 2. 源文件不存在
     * 3. 源文件是个目录
     * 4. 目标文件没有写权限
     * 5. 源文件和目标文件被指向了同一个文件
     *
     * @param srcFile          源文件
     * @param destFile         目标文件
     * @param preserveFileDate 目标文件产生后，是否修改为当前时间
     * @throws IOException IO异常
     */
    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        }

        if (!srcFile.exists()) {
            throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
        }

        // 源文件存在，但是个目录，不允许
        if (destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile + "' exists but is a directory");
        }

        // 源文件和目标文件指向了同一个文件，不允许
        if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("Source '" + srcFile + "' and destination '" + destFile + "' are the same");
        }

        // 目标文件存在，没有写权限
        if (destFile.exists() && !destFile.canWrite()) {
            throw new IOException("Destination '" + destFile + "' exists but is read-only");
        }

        // 目标文件没有父目录，就创建之
        File parentFile = destFile.getParentFile();
        if (null != parentFile) {
            if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination '" + parentFile + "' directory cannot be created");
            }
        }

        doCopyFile(srcFile, destFile, preserveFileDate);
    }

    private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            input = fis.getChannel();
            output = fos.getChannel();
            long size = input.size();
            long pos = 0;
            long count;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
                pos += output.transferFrom(input, pos, count);
            }
        } finally {
            closeQuietly(output);
            closeQuietly(fos);
            closeQuietly(input);
            closeQuietly(fis);
        }

        if (srcFile.length() != destFile.length()) {
            throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "'");
        }

        // 修改文件最后修改时间
        if (preserveFileDate) {
            destFile.setLastModified(srcFile.lastModified());
        }
    }

    // ////////////////////递归删除文件夹下的所有文件////////////////////

    public static void deleteFileOrDirectoryQuietly(String filePath) {
        try {
            deleteFileOrDirectory(filePath, true);
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * 删除文件，并会递归删除其下面的文件
     *
     * @param filePath       指定文件
     * @param deleteThisPath 是否删除本文件
     * @throws IOException
     */
    public static void deleteFileOrDirectory(String filePath, boolean deleteThisPath) throws IOException {
        if (null == filePath || filePath.length() <= 0) {
            return;
        }

        File file = new File(filePath);

        //如果是目录，或者子文件或目录进行递归处理
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File f : files) {
                    deleteFileOrDirectory(f.getAbsolutePath(), true);
                }
            }
        }

        if (deleteThisPath) {
            if (!file.isDirectory()) {
                file.delete();
            } else {
                File[] files = file.listFiles();
                if (null == files || files.length == 0) {
                    file.delete();
                }
            }
        }
    }

    // ////////////////////递归获取文件夹下面的所有文件////////////////////

    public static List<File> getFileListByPath(String path) {
        return getFileListByPath(path, null);
    }

    public static List<File> getFileListByPath(String path, FileFilter filter) {
        File directory = new File(path);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("File [%s] not exist. Or not directory.", path));
        }

        List<File> fileList = new ArrayList<>();
        recursiverFile(directory, filter, fileList);
        return fileList;
    }

    // ////////////////////操作Properties文件////////////////////

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
        } catch (IOException e) {
            throw new RuntimeException("Could not read Properties[" + path + "]", e);
        } finally {
            closeQuietly(in);
        }

        return properties;
    }

    public static Properties readProperties(InputStream in) {
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Could not read Properties", e);
        } finally {
            closeQuietly(in);
        }

        return properties;
    }

    public static void writeProperties(String path, Properties properties) {
        OutputStream out = null;

        try {
            out = new FileOutputStream(path);
            properties.store(out, null);
        } catch (IOException e) {
            throw new RuntimeException("Could not write Properties[" + path + "]", e);
        } finally {
            closeQuietly(out);
        }
    }

    /**
     * 递归获取目录下的所有文件
     *
     * @param file     指定目录
     * @param filter   文件过滤器
     * @param fileList 文件列表
     */
    private static void recursiverFile(File file, FileFilter filter, List<File> fileList) {
        File[] children = null == filter ? file.listFiles() : file.listFiles(filter);

        if (null != children) {
            for (File f : children) {
                if (f.isDirectory()) {
                    recursiverFile(f, filter, fileList);
                } else {
                    fileList.add(f);
                }
            }
        }
    }

    private static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(String.format("file %s exists but is a directory.", file.getPath()));
            }
            if (!file.canWrite()) {
                throw new IOException(String.format("file %s cannot be written to.", file.getPath()));
            }
        } else {
            File parent = file.getParentFile();
            if (null != parent && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException(String.format("directory %s could not be created.", parent.getPath()));
            }
        }
        return new FileOutputStream(file, append);
    }

    private static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(String.format("File %s exists but is a directory.", file.getPath()));
            }

            if (!file.canRead()) {
                throw new IOException(String.format("File %s cannot be written to.", file.getPath()));
            }
        } else {
            throw new FileNotFoundException(String.format("File %s does not exist.", file.getPath()));
        }

        return new FileInputStream(file);
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    private static byte[] toByteArray(InputStream input, long longSize) throws IOException {
        if (longSize > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("流的期望长度不能超过int能表示的范围，当前长度： " + longSize);
        }

        // 转成int
        int size = (int)longSize;

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
