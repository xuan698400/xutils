package com.xuan.xutils.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 文件操作工具类
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-9-4 下午7:24:34 $
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

    /**
     * 截取文件的后缀名，例如文件名是xuan.jpg时，返回的后缀名时jpg
     *
     * @param fileName 文件名
     * @return 后缀名
     */
    public static String getExtension(String fileName) {
        if (null == fileName) {
            return EMPTY;
        }

        int pointIndex = fileName.lastIndexOf(".");
        return pointIndex > 0 && pointIndex < fileName.length() ? fileName
                .substring(pointIndex + 1).toLowerCase() : EMPTY;
    }

    // ////////////////////字节写入读出文件方法，一般可以用来写图片，声音等////////////////////

    /**
     * 字节写入到文件中
     *
     * @param file   文件
     * @param data   字节数据
     * @param append 是否从文件后面添加
     * @throws IOException
     */
    public static void writeByteArrayToFile(File file, byte[] data,
                                            boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            out.write(data);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 读出文件中的字节
     *
     * @param file 文件
     * @return 字节数组
     * @throws IOException
     */
    public static byte[] readFileToByteArray(File file) throws IOException {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtils.toByteArray(in, file.length());
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    // ////////////////////字符串从文件中写入读出方法////////////////////

    /**
     * 数据写入文件
     *
     * @param file     要写入的文件
     * @param data     数据
     * @param encoding 指定编码
     * @param append   是否追加
     * @throws IOException
     */
    public static void writeStringToFile(File file, String data,
                                         String encoding, boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            IOUtils.write(data, out, encoding);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 从文件中以指定编码读取成字符串
     *
     * @param file     文件
     * @param encoding 编码
     * @return
     * @throws IOException
     */
    public static String readFileToString(File file, String encoding)
            throws IOException {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtils.toString(in, encoding);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    // ////////////////////打开输入输出流方法////////////////////

    /**
     * 打开一个文件的写入流，文件不存在会自动创建
     *
     * @param file   文件
     * @param append 是否以追加的方式写入
     * @return
     * @throws IOException
     */
    public static FileOutputStream openOutputStream(File file, boolean append)
            throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file
                        + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file
                        + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent
                            + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }

    /**
     * 打开文件输入流，校验友好的提示
     *
     * @param file 要打开的文件
     * @return
     * @throws IOException
     */
    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file
                        + "' exists but is a directory");
            }

            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file
                    + "' does not exist");
        }

        return new FileInputStream(file);
    }

    // ////////////////////文件单位字节友好输出////////////////////

    /**
     * 友好的显示单位，舍弃有点问题，例如：byteCountToDisplaySize(2047)显示1K，明显是舍弃了
     *
     * @param size byte单位值
     * @return
     */
    public static String byteCountToDisplaySize(long size) {
        String displaySize;
        if (size / ONE_EB > 0) {
            displaySize = String.valueOf(size / ONE_EB) + " EB";
        } else if (size / ONE_EB > 0) {
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
     * @throws IOException
     */
    public static void moveDirectoryToDirectory(File src, File destDir,
                                                boolean isCover) throws IOException {
        if (src == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException(
                    "Destination directory must not be null");
        }

        if (destDir.exists()) {
            if (isCover) {
                deleteFileOrDirectoryQuietly(destDir.getPath());
            } else {
                throw new IOException(
                        "Destination directory is exists and isCover=false");
            }
        }

        destDir.mkdirs();
        if (!destDir.exists()) {
            throw new FileNotFoundException("Destination directory '" + destDir
                    + "' create failed]");
        }
        if (!destDir.isDirectory()) {
            throw new IOException("Destination '" + destDir
                    + "' is not a directory");
        }

        boolean rename = src.renameTo(destDir);
        if (!rename) {
            if (destDir.getCanonicalPath().startsWith(src.getCanonicalPath())) {
                throw new IOException("Cannot move directory: " + src
                        + " to a subdirectory of itself: " + destDir);
            }
            copyDirectoryToDirectory(src, destDir);
            deleteFileOrDirectoryQuietly(src.getPath());
            if (src.exists()) {
                throw new IOException("Failed to delete original directory '"
                        + src + "' after copy to '" + destDir + "'");
            }
        }
    }

    /**
     * 剪切文件到文件夹
     *
     * @param srcFile 源文件
     * @param destDir 目的文件夹
     * @param isCover 存在是否覆盖
     * @throws IOException
     */
    public static void moveFileToDirectory(File srcFile, File destDir,
                                           boolean isCover) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException(
                    "Destination directory must not be null");
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        if (!destDir.exists()) {
            throw new FileNotFoundException("Destination directory '" + destDir
                    + "' create failed]");
        }

        if (!destDir.isDirectory()) {
            throw new IOException("Destination '" + destDir
                    + "' is not a directory");
        }

        moveFile(srcFile, new File(destDir, srcFile.getName()), isCover);
    }

    /**
     * 剪切文件到文件
     *
     * @param srcFile  源文件
     * @param destFile 目的文件
     * @param isCover  存在是否覆盖
     * @throws IOException
     */
    public static void moveFile(File srcFile, File destFile, boolean isCover)
            throws IOException {
        if (null == srcFile) {
            throw new NullPointerException("Source must not be null");
        }
        if (null == destFile) {
            throw new NullPointerException("Destination must not be null");
        }

        if (!srcFile.exists()) {
            throw new FileNotFoundException("Source '" + srcFile
                    + "' does not exist");
        }
        if (srcFile.isDirectory()) {
            throw new IOException("Source '" + srcFile + "' is a directory");
        }

        if (destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile
                    + "' is a directory");
        }

        if (destFile.exists()) {
            if (isCover) {
                deleteFileOrDirectoryQuietly(destFile.getPath());
            } else {
                throw new IOException(
                        "Destination directory is exists and isCover=false");
            }
        }

        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile, destFile);
            if (!srcFile.delete()) {
                deleteFileOrDirectoryQuietly(destFile.getPath());
                throw new IOException("Failed to delete original file '"
                        + srcFile + "' after copy to '" + destFile + "'");
            }
        }
    }

    // ////////////////////文件夹内容拷贝到指定文件夹中////////////////////

    /**
     * 拷贝文件夹到文件夹
     *
     * @param srcDir  源文件夹
     * @param destDir 目的文件夹
     * @throws IOException
     */
    public static void copyDirectoryToDirectory(File srcDir, File destDir)
            throws IOException {
        copyDirectoryToDirectory(srcDir, new File(destDir, srcDir.getName()),
                null);
    }

    /**
     * 拷贝文件夹到文件夹
     *
     * @param srcDir  源文件夹
     * @param destDir 目的文件夹
     * @param filter  文件过滤器
     * @throws IOException
     */
    public static void copyDirectoryToDirectory(File srcDir, File destDir,
                                                FileFilter filter) throws IOException {
        if (srcDir == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination must not be null");
        }

        if (srcDir.exists() && !srcDir.isDirectory()) {
            throw new IllegalArgumentException("Source '" + destDir
                    + "' is not a directory");
        }
        if (destDir.exists() && !destDir.isDirectory()) {
            throw new IllegalArgumentException("Destination '" + destDir
                    + "' is not a directory");
        }

        if (!srcDir.exists()) {
            throw new FileNotFoundException("Source '" + srcDir
                    + "' does not exist");
        }

        if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
            throw new IOException("Source '" + srcDir + "' and destination '"
                    + destDir + "' are the same");
        }

        // Cater for destination being directory within the source directory
        // (see IO-141)
        List<String> exclusionList = null;
        if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath())) {
            File[] srcFiles = null == filter ? srcDir.listFiles() : srcDir
                    .listFiles(filter);
            if (srcFiles != null && srcFiles.length > 0) {
                exclusionList = new ArrayList<String>(srcFiles.length);
                for (File srcFile : srcFiles) {
                    File copiedFile = new File(destDir, srcFile.getName());
                    exclusionList.add(copiedFile.getCanonicalPath());
                }
            }
        }
        doCopyDirectory(srcDir, destDir, filter, true, exclusionList);
    }

    private static void doCopyDirectory(File srcDir, File destDir,
                                        FileFilter filter, boolean preserveFileDate,
                                        List<String> exclusionList) throws IOException {
        // recurse
        File[] srcFiles = (null == filter) ? srcDir.listFiles() : srcDir
                .listFiles(filter);
        if (srcFiles == null) { // null if abstract pathname does not denote a
            // directory, or if an I/O error occurs
            throw new IOException("Failed to list contents of " + srcDir);
        }
        if (destDir.exists()) {
            if (!destDir.isDirectory()) {
                throw new IOException("Destination '" + destDir
                        + "' exists but is not a directory");
            }
        } else {
            if (!destDir.mkdirs() && !destDir.isDirectory()) {
                throw new IOException("Destination '" + destDir
                        + "' directory cannot be created");
            }
        }
        if (!destDir.canWrite()) {
            throw new IOException("Destination '" + destDir
                    + "' cannot be written to");
        }

        for (File srcFile : srcFiles) {
            File dstFile = new File(destDir, srcFile.getName());
            if (exclusionList == null
                    || !exclusionList.contains(srcFile.getCanonicalPath())) {
                if (srcFile.isDirectory()) {
                    doCopyDirectory(srcFile, dstFile, filter, preserveFileDate,
                            exclusionList);
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
    public static void copyFileToDirectory(File srcFile, File destDir)
            throws IOException {
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
    public static void copyFileToDirectory(File srcFile, File destDir,
                                           boolean preserveFileDate) throws IOException {
        if (null == destDir) {
            throw new NullPointerException("Destination must not be null");
        }
        if (destDir.exists() && !destDir.isDirectory()) {
            throw new IllegalArgumentException("Destination '" + destDir
                    + "' is not a directory");
        }
        File destFile = new File(destDir, srcFile.getName());
        copyFile(srcFile, destFile, preserveFileDate);
    }

    // ////////////////////文件拷贝到文件////////////////////

    /**
     * 拷贝文件到文件
     *
     * @param srcFile  源文件
     * @param destFile 目的文件
     * @throws IOException
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        copyFile(srcFile, destFile, true);
    }

    /**
     * 拷贝文件到文件
     *
     * @param srcFile          源文件
     * @param destFile         目的文件
     * @param preserveFileDate 是否修改时间
     * @throws IOException
     */
    public static void copyFile(File srcFile, File destFile,
                                boolean preserveFileDate) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        }

        if (!srcFile.exists()) {// 源不存在
            throw new FileNotFoundException("Source '" + srcFile
                    + "' does not exist");
        }

        if (destFile.isDirectory()) {// 源存在，但是是个目录
            throw new IOException("Destination '" + destFile
                    + "' exists but is a directory");
        }

        if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {// 指向了同一个文件
            throw new IOException("Source '" + srcFile + "' and destination '"
                    + destFile + "' are the same");
        }

        if (destFile.exists() && !destFile.canWrite()) {// 目标文件存在，没有写权限
            throw new IOException("Destination '" + destFile
                    + "' exists but is read-only");
        }

        // 创建父文件夹
        File parentFile = destFile.getParentFile();
        if (null != parentFile) {
            if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination '" + parentFile
                        + "' directory cannot be created");
            }
        }

        doCopyFile(srcFile, destFile, preserveFileDate);
    }

    private static void doCopyFile(File srcFile, File destFile,
                                   boolean preserveFileDate) throws IOException {
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
            long count = 0;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE
                        : size - pos;
                pos += output.transferFrom(input, pos, count);
            }
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fis);
        }

        if (srcFile.length() != destFile.length()) {
            throw new IOException("Failed to copy full contents from '"
                    + srcFile + "' to '" + destFile + "'");
        }

        if (preserveFileDate) {// 修改文件最后修改时间
            destFile.setLastModified(srcFile.lastModified());
        }
    }

    // ////////////////////递归删除文件夹下的所有文件////////////////////

    /**
     * 删除指定目录下文件及目录，默认本身也会被删除
     *
     * @param filePath 文件或者文件夹路径
     */
    public static void deleteFileOrDirectoryQuietly(String filePath) {
        try {
            deleteFileOrDirectory(filePath, true);
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param filePath       文件或者文件夹路径
     * @param deleteThisPath 是否需要删除这个本身指定的文件或者文件夹
     */
    public static void deleteFileOrDirectory(String filePath,
                                             boolean deleteThisPath) throws IOException {
        if (null != filePath && filePath.length() > 0) {
            File file = new File(filePath);

            if (file.isDirectory()) {// 处理目录
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFileOrDirectory(files[i].getAbsolutePath(), true);
                }
            }

            if (deleteThisPath) {
                if (!file.isDirectory()) {// 如果是文件，删除
                    file.delete();
                } else {// 目录
                    if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                        file.delete();
                    }
                }
            }
        }
    }

    // ////////////////////递归获取文件夹下面的所有文件////////////////////

    /**
     * 获取指定路径下的所有文件
     *
     * @param path 指定路径
     * @return
     */
    public static List<File> getFileListByPath(String path) {
        return getFileListByPath(path, null);
    }

    /**
     * 获取指定路径下的所有文件
     *
     * @param path   指定路径
     * @param filter 文件过滤器
     * @return
     */
    public static List<File> getFileListByPath(String path, FileFilter filter) {
        File directory = new File(path);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Nonexistent directory[" + path
                    + "]");
        }

        return new Recursiver().getFileList(directory, filter);
    }

    // ////////////////////操作Properties文件////////////////////

    /**
     * 读取指定路径的Properties文件
     *
     * @param path 路径
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
        } catch (IOException e) {
            throw new RuntimeException("Could not read Properties[" + path + "]", e);
        } finally {
            IOUtils.closeQuietly(in);
        }

        return properties;
    }

    /**
     * 从输入流读取Properties对象
     *
     * @param in 输入流
     * @return Properties对象
     */
    public static Properties readProperties(InputStream in) {
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Could not read Properties", e);
        } finally {
            IOUtils.closeQuietly(in);
        }

        return properties;
    }

    /**
     * 把Properties对象写到指定路径的文件里
     *
     * @param path       路进
     * @param properties Properties对象
     */
    public static void writeProperties(String path, Properties properties) {
        OutputStream out = null;

        try {
            out = new FileOutputStream(path);
            properties.store(out, null);
        } catch (IOException e) {
            throw new RuntimeException("Could not write Properties[" + path + "]", e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 递归获取指定目录下的所有文件的遍历器
     *
     * @author xuan
     * @version $Revision: 1.0 $, $Date: 2013-9-5 下午1:13:17 $
     */
    private static class Recursiver {
        private static ArrayList<File> files = new ArrayList<File>();

        public List<File> getFileList(File file, FileFilter filter) {
            File children[] = null == filter ? file.listFiles() : file
                    .listFiles(filter);

            for (int i = 0; i < children.length; i++) {
                if (children[i].isDirectory()) {
                    new Recursiver().getFileList(children[i], filter);
                } else {
                    files.add(children[i]);
                }
            }

            return files;
        }
    }

}
