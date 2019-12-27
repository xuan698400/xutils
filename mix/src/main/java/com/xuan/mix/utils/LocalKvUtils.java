package com.xuan.mix.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * 存储在本地计算机文件系统的KV存储工具类
 *
 * @author xuan
 * @since 2019/7/1
 */
public class LocalKvUtils {

    public static void put(String path, String key, String value) {
        Properties properties = getPropertiesFromPath(path);
        if (null == properties) {
            return;
        }

        properties.put(key, value);
        writeProperties(path, properties);
    }

    public static void put(String path, Map<String, String> map) {
        if (null == map) {
            return;
        }

        Properties properties = getPropertiesFromPath(path);
        if (null == properties) {
            return;
        }

        properties.putAll(map);
        writeProperties(path, properties);
    }

    public static String get(String path, String key) {
        Properties properties = getPropertiesFromPath(path);
        if (null == properties) {
            return null;
        }

        return properties.getProperty(key);
    }

    public static void remove(String path, String key) {
        Properties properties = getPropertiesFromPath(path);
        if (null == properties) {
            return;
        }

        properties.remove(key);
        writeProperties(path, properties);
    }

    public static void removeAll(String path) {
        if (null == path) {
            return;
        }

        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    private static Properties getPropertiesFromPath(String path) {
        initFileForPath(path);
        return readProperties(path);
    }

    private static void initFileForPath(String path) {
        if (null == path) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Properties readProperties(String path) {
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
            throw new RuntimeException(String.format("Could not read Properties[%s]", path), e);
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
            throw new RuntimeException(String.format("Could not write Properties[%s]", path), e);
        } finally {
            closeQuietly(out);
        }
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

}
