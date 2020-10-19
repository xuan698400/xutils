package com.xuan.mix.compile.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.xuan.mix.compile.CompileException;

/**
 * @author xuan
 * @since 2020/10/19
 */
class FileUtil {
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    static void createFile(File file) {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new CompileException(
                        "Compile log file can't be created, file:" + file.getName());
                }
            } catch (IOException e) {
                throw new CompileException(
                    "Compile log file create exception, file:" + file.getName(), e);
            }
        }
    }

    static void createPackageDirs(File parent, String packageName) {

        String[] packagePaths = packageName.split("\\.");
        for (String packagePath : packagePaths) {
            parent = new File(parent, packagePath);
            if (!parent.exists()) {
                if (!parent.mkdir()) {
                    throw new CompileException("Failed to mkdir, dir name is " + parent);
                }
            }
        }
    }

    static void createDir(String dirPath) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            if (!dirFile.mkdir()) {
                throw new CompileException(
                    "Output directory for class can't be created, directory:" + dirPath);
            }
        }
    }

    static byte[] readFileToByteArray(final File file) throws IOException {
        try (InputStream in = openInputStream(file)) {
            long fileLength = file.length();
            return fileLength > 0 ? toByteArray(in, (int)fileLength) : toByteArray(in);
        }
    }

    private static FileInputStream openInputStream(final File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

    private static byte[] toByteArray(final InputStream input, final int size) throws IOException {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }

        if (size == 0) {
            return new byte[0];
        }

        final byte[] data = new byte[size];
        int offset = 0;
        int read;

        while (offset < size && (read = input.read(data, offset, size - offset)) != EOF) {
            offset += read;
        }

        if (offset != size) {
            throw new IOException("Unexpected read size. current: " + offset + ", expected: " + size);
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
