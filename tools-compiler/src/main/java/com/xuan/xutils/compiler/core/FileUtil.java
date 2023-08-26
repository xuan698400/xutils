package com.xuan.xutils.compiler.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.xuan.xutils.compiler.CompileException;

/**
 * 文件操作工具类
 *
 * @author xuan
 * @since 2020/10/19
 */
public class FileUtil {
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static void createFile(File file) {
        if (file.exists()) {
            return;
        }

        try {
            boolean b = file.createNewFile();
            if (!b) {
                throw new CompileException(String.format("Failed to createNewFile[%s]", file.getName()));
            }
        } catch (IOException e) {
            throw new CompileException(String.format("Exception to createNewFile[%s]", file.getName()), e);
        }
    }

    public static void createPackageDirs(File parent, String packageName) {
        String[] packagePaths = packageName.split("\\.");
        for (String packagePath : packagePaths) {
            parent = new File(parent, packagePath);
            if (!parent.exists()) {
                if (!parent.mkdirs()) {
                    throw new CompileException(String.format("Failed to mkdir[%s]", parent.getName()));
                }
            }
        }
    }

    public static void createDir(String dirPath) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            if (!dirFile.mkdirs()) {
                throw new CompileException(String.format("Failed to mkdirs[%s]", dirFile));
            }
        }
    }

    public static byte[] readFileToByteArray(final File file) throws IOException {
        try (InputStream in = openInputStream(file)) {
            long fileLength = file.length();
            return fileLength > 0 ? toByteArray(in, (int)fileLength) : toByteArray(in);
        }
    }

    private static FileInputStream openInputStream(final File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new CompileException(String.format("File exists but is a directory. file[%s]", file.getName()));
            }
            if (!file.canRead()) {
                throw new CompileException(String.format("File cannot be read. file[%s]", file.getName()));
            }
        } else {
            throw new CompileException(String.format("File not exists. file[%s]", file.getName()));
        }
        return new FileInputStream(file);
    }

    private static byte[] toByteArray(final InputStream input, final int size) throws IOException {
        final byte[] data = new byte[size];
        int offset = 0;
        int read;

        while (offset < size && (read = input.read(data, offset, size - offset)) != EOF) {
            offset += read;
        }

        if (offset != size) {
            throw new CompileException(String.format("Unexpected read size. current:%s, expected:%s", offset, size));
        }

        return data;
    }

    private static byte[] toByteArray(final InputStream input) throws IOException {
        try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            int n;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            while (EOF != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        }
    }

}
