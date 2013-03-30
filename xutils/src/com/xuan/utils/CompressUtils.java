package com.xuan.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 对文件、输出流提供压缩、解压操作的工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:51:01 $
 */
public abstract class CompressUtils {

    /**
     * 压缩处理类，客户端通过实现此方法来将数据写入输出流。
     */
    public static interface CompressHandler {

        /**
         * 对输出流进行处理的方法，可以将需要进行压缩的数据写入该输出流中。
         * <p>
         * {@link OutputStream#close()} 方法可以不调用，因为在工具方法中会进行关闭操作。
         * 
         * @param out
         *            输出流
         * @throws IOException
         *             发生 I/O 错误时应当抛出此异常
         */
        void handle(OutputStream out) throws IOException;
    }

    private static final int BUFFER_SIZE = 8192;

    /**
     * 以 GZip 方式对输出流进行压缩后保存到文件。<strong>注意：如果目标文件已经存在，则会覆盖已有文件的内容。</strong>
     * <p>
     * 以下是两个使用的例子：
     * 
     * <pre>
     * CompressUtils.gzCompress(&quot;foo.txt&quot;, new CompressHandler() {
     *     &#064;Override
     *     public void handle(OutputStream out) throws IOException {
     *         out.write(&quot;This is a test.&quot;.getBytes());
     *     }
     * });
     * </pre>
     * 
     * <pre>
     * CompressUtils.gzCompress(&quot;bar.txt&quot;, new CompressHandler() {
     *     &#064;Override
     *     public void handle(OutputStream out) throws IOException {
     *         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
     *         writer.write(&quot;This is a test.&quot;);
     *         writer.flush(); // 刷新缓冲
     *     }
     * });
     * </pre>
     * 
     * @param dstFile
     *            目标文件，即压缩后的文件
     * @param handler
     *            压缩输出流处理类
     * @throws IOException
     *             当压缩过程中发生错误等情况下抛出此异常
     */
    public static void gzCompress(String dstFile, CompressHandler handler) throws IOException {
        if (!dstFile.endsWith(".gz")) {
            dstFile = dstFile + ".gz";
        }

        GZIPOutputStream gzos = null;
        FileChannel channel = null;
        FileLock lock = null;

        try {
            FileOutputStream fos = new FileOutputStream(dstFile);
            channel = fos.getChannel();
            lock = channel.lock();

            gzos = new GZIPOutputStream(fos);
            handler.handle(gzos);
        }
        catch (IOException e) {
            throw new IOException("Error occurred while compressing stream into [" + dstFile + "].", e);
        }
        finally {
            clean(null, gzos, lock, channel);
        }
    }

    /**
     * 对指定的文件以 GZip 方式进行压缩。<strong>注意：如果目标文件已经存在，则会覆盖已有文件的内容。</strong>
     * 
     * @param srcFile
     *            源文件，即待压缩的文件
     * @param dstFile
     *            目标文件，即压缩后的文件
     * @throws IOException
     *             当源文件不存在、压缩过程中发生错误等情况下抛出此异常
     */
    public static void gzCompress(String srcFile, String dstFile) throws IOException {
        File file2Compress = new File(srcFile);
        if (!file2Compress.exists()) {
            throw new IOException("The file to compress named [" + srcFile + "] does not exist.");
        }

        if (!dstFile.endsWith(".gz")) {
            dstFile = dstFile + ".gz";
        }

        FileInputStream fis = null;
        GZIPOutputStream gzos = null;
        FileChannel channel = null;
        FileLock lock = null;

        try {
            FileOutputStream fos = new FileOutputStream(dstFile);

            // 取得文件锁，保证在写文件过程中不会被其他进程读到不完整的数据
            channel = fos.getChannel();
            lock = channel.lock();

            fis = new FileInputStream(srcFile);
            gzos = new GZIPOutputStream(fos);

            byte[] buffer = new byte[BUFFER_SIZE];
            for (int len = 0; (len = fis.read(buffer)) != -1;) {
                gzos.write(buffer, 0, len);
            }
        }
        catch (IOException e) {
            throw new IOException("Error occurred while compressing [" + srcFile + "] into [" + dstFile + "].", e);
        }
        finally {
            clean(fis, gzos, lock, channel);
        }
    }

    /**
     * 对指定的文件以 GZip 方式进行解压缩。<strong>注意：如果目标文件已经存在，则会覆盖已有文件的内容。</strong>
     * 
     * @param srcFile
     *            源文件，即待解压的文件
     * @param dstFile
     *            目标文件，即解压后的文件
     * @throws IOException
     *             当源文件不存在、解压缩过程中发生错误等情况下抛出此异常
     */
    public static void gzDecompress(String srcFile, String dstFile) throws IOException {
        File compressedFile = new File(srcFile);
        if (!compressedFile.exists()) {
            throw new IOException("The file to decompress named [" + srcFile + "] does not exist.");
        }

        GZIPInputStream gzis = null;
        FileOutputStream fos = null;

        try {
            gzis = new GZIPInputStream(new FileInputStream(srcFile));
            fos = new FileOutputStream(dstFile);

            byte[] buffer = new byte[BUFFER_SIZE];
            for (int len = 0; (len = gzis.read(buffer)) != -1;) {
                fos.write(buffer, 0, len);
            }
        }
        catch (IOException e) {
            throw new IOException("Error occurred while decompressing [" + srcFile + "] into [" + dstFile + "].", e);
        }
        finally {
            clean(gzis, fos);
        }
    }

    /**
     * 对指定的文件以 Zip 方式进行压缩，目前只支持单个文件，不支持对目录进行压缩操作。
     * <p>
     * <strong>注意：如果目标文件已经存在，则会覆盖已有文件的内容。</strong>
     * 
     * @param srcFile
     *            源文件，即待压缩的文件
     * @param dstFile
     *            目标文件，即压缩后的文件
     * @throws IOException
     *             当源文件不存在、压缩过程中发生错误等情况下抛出此异常
     */
    public static void zipCompress(String srcFile, String dstFile) throws IOException {
        File file2Compress = new File(srcFile);
        if (!file2Compress.exists()) {
            throw new IOException("The file to compress named [" + srcFile + "] does not exist.");
        }

        if (!dstFile.endsWith(".zip")) {
            dstFile = dstFile + ".zip";
        }

        FileInputStream fis = null;
        ZipOutputStream zos = null;
        FileChannel channel = null;
        FileLock lock = null;

        try {
            FileOutputStream fos = new FileOutputStream(dstFile);
            channel = fos.getChannel();
            lock = channel.lock();

            fis = new FileInputStream(srcFile);
            zos = new ZipOutputStream(fos);

            ZipEntry entry = new ZipEntry(file2Compress.getName());
            entry.setCompressedSize(file2Compress.length());
            entry.setTime(file2Compress.lastModified());
            zos.putNextEntry(entry);

            byte[] buffer = new byte[BUFFER_SIZE];
            for (int len = 0; (len = fis.read(buffer)) != -1;) {
                zos.write(buffer, 0, len);
            }
        }
        catch (IOException e) {
            throw new IOException("Error occurred while compressing [" + srcFile + "] into [" + dstFile + "].");
        }
        finally {
            clean(fis, zos, lock, channel);
        }
    }

    /**
     * 对指定的文件以 Zip 方式进行解压缩。目前只支持单个文件，不支持对目录进行解压操作。
     * <p>
     * <strong>注意：如果目标文件已经存在，则会覆盖已有文件的内容。</strong>
     * 
     * @param srcFile
     *            源文件，即待解压的文件
     * @param dstFile
     *            目标文件，即解压后的文件
     * @throws IOException
     *             当源文件不存在、解压缩过程中发生错误等情况下抛出此异常
     */
    public static void zipDecompress(String srcFile, String dstFile) throws IOException {
        File compressedFile = new File(srcFile);
        if (!compressedFile.exists()) {
            throw new IOException("The file to decompress named [" + srcFile + "] does not exist.");
        }

        ZipInputStream zis = null;
        FileOutputStream fos = null;

        try {
            zis = new ZipInputStream(new FileInputStream(srcFile));
            fos = new FileOutputStream(dstFile);

            ZipEntry entry = zis.getNextEntry();
            if (entry == null) {
                throw new IOException("The file to decompress named [" + srcFile + "] has no zip entry.");
            }

            byte[] buffer = new byte[BUFFER_SIZE];
            for (int len = 0; (len = zis.read(buffer)) != -1;) {
                fos.write(buffer, 0, len);
            }
        }
        catch (IOException e) {
            throw new IOException("Error occurred while decompressing [" + srcFile + "] into [" + dstFile + "].");
        }
        finally {
            clean(zis, fos);
        }
    }

    /**
     * 释放资源，关闭输入输出流。
     */
    private static void clean(InputStream in, OutputStream out) throws IOException {
        FileUtils.close(in);
        FileUtils.close(out);
    }

    /**
     * 释放资源，包括关闭输入输出流、关闭文件通道、释放文件锁。
     */
    private static void clean(InputStream in, OutputStream out, FileLock lock, FileChannel channel) throws IOException {
        FileUtils.close(in);

        // 释放文件锁
        if (lock != null) {
            lock.release();
        }

        // 在关闭压缩输出流之后再关闭通道，如果先关闭通道会导致 压缩文件的格式错误
        FileUtils.close(out);
        if (channel != null) {
            channel.close();
        }
    }

}
