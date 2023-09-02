package com.xuan.xutils.base.file.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author xuan
 * @since 2022/10/18
 */
public class ZipUtils {

    public static void zip(List<File> originFileList, File zipFile) throws FileNotFoundException, IOException {
        byte[] buf = new byte[1024];

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        for (File originFile : originFileList) {
            FileInputStream in = new FileInputStream(originFile);
            zipOut.putNextEntry(new ZipEntry(originFile.getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                zipOut.write(buf, 0, len);
            }
            zipOut.closeEntry();
            in.close();
        }
        zipOut.close();
        System.out.println("压缩成功");
    }

    public static void unzip(File zipFile, String destDir) throws IOException {
        File destFile = new File(destDir);
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        // 解决zip文件中有中文目录或者中文文件
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String curEntryName = entry.getName();
            // 判断文件名路径是否存在文件夹
            int endIndex = curEntryName.lastIndexOf('/');
            // 替换
            String outPath = (destDir + curEntryName).replaceAll("\\*", "/");
            if (endIndex != -1) {
                File file = new File(outPath.substring(0, outPath.lastIndexOf("/")));
                if (!file.exists()) {
                    file.mkdirs();
                }
            }

            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            File outFile = new File(outPath);
            if (outFile.isDirectory()) {
                continue;
            }
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            InputStream in = zip.getInputStream(entry);
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("解压成功");
    }

}
