package com.xuan.moho.framework.extension.framework.core;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class ClassUtil {

    public static Set<Class> getAllClasses(ClassFilter filter, Set<String> packageNameSet) {
        if (null == packageNameSet || packageNameSet.isEmpty()) {
            throw new RuntimeException("packageNameSet is null or empty");
        }

        Set<Class> allClassSet = new HashSet<>();
        for (String packageName : packageNameSet) {
            Set<Class> temp = doGetClassesUnderPackage(packageName);
            allClassSet.addAll(temp);
        }

        Set<Class> filteredClassSet = new HashSet<>();
        for (Class c : allClassSet) {
            if (null != filter && !filter.accept(c)) {
                continue;
            }
            filteredClassSet.add(c);
        }

        return filteredClassSet;
    }

    private static Set<Class> doGetClassesUnderPackage(String packageName) {

        if (null == packageName || packageName.trim().isEmpty()) {
            throw new ExtensionException("packageName is null or empty.");
        }

        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(packagePath);
        } catch (IOException e) {
            throw new ExtensionException(
                String.format("ClassLoader.getResources IOException for packagePath:%s", packagePath),
                e);
        }

        Set<Class> classSet = new HashSet<>();

        while (resources.hasMoreElements()) {
            URL resourceUrl = resources.nextElement();
            if (isJarFile(resourceUrl)) {
                fetchClassFromJar(resourceUrl, packageName, classSet);
            } else {
                File file = toFile(resourceUrl);
                if (file.exists() && file.isDirectory()) {
                    fetchClassFromDirectory(file, packageName, classSet);
                }
            }
        }

        return classSet;
    }

    /**
     * 从指定目录下遍历出所有类
     *
     * @param dir         指定目录
     * @param packageName 类包名
     * @param classSet    class集合
     */
    private static void fetchClassFromDirectory(File dir, String packageName, Set<Class> classSet) {

        if (!dir.exists()) {
            return;
        }

        File[] files = dir.listFiles();
        if (null == files) {
            return;
        }

        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                String subPackageName = packageName + (packageName.isEmpty() ? "" : ".") + fileName;
                fetchClassFromDirectory(file, subPackageName, classSet);
            } else if (fileName.endsWith(".class")) {
                String className = packageName + (packageName.isEmpty() ? "" : ".") + fileName
                    .substring(0, fileName.length() - 6);
                try {
                    classSet.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new ExtensionException(
                        String.format("Class.forName ClassNotFoundException for className:%s", className), e);
                }
            }
        }
    }

    /**
     * 从Jar文件中遍历出所有类（可限定包）
     *
     * @param jarUrl      Jar文件Url
     * @param packageName 限定包进行过滤
     * @param classSet    class集合
     */
    private static void fetchClassFromJar(URL jarUrl, String packageName, Set<Class> classSet) {
        if (packageName.isEmpty()) {
            return;
        }

        String packageNameFilter = packageName.replace('.', '/');
        Enumeration<JarEntry> allEntries = toJarFile(jarUrl).entries();
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        while (allEntries.hasMoreElements()) {
            JarEntry jarEntry = allEntries.nextElement();
            String entryName = jarEntry.getName();
            if (!jarEntry.isDirectory() && entryName.startsWith(packageNameFilter) && entryName.endsWith(
                ".class")) {
                String className = jarEntry.getName().replaceFirst("\\.class$", "").replace("/", ".");
                try {
                    classSet.add(Class.forName(className, false, classLoader));
                } catch (ClassNotFoundException e) {
                    LogUtil.error(String.format("Class.forName ClassNotFoundException for className:%s", className), e);
                }
            }
        }
    }

    private static boolean isJarFile(URL url) {
        return url.toString().startsWith("jar:file:");
    }

    private static File toFile(URL url) {
        try {
            String filepath = URLDecoder.decode(url.getFile(), "UTF-8");
            return new File(filepath);
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(
                String.format("URLDecoder.decode and new File UnsupportedEncodingException for file:%s", url.getFile()),
                e);
        }
        return new File(url.toString());
    }

    private static JarFile toJarFile(final URL url) {
        String rawJarPath;
        try {
            rawJarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new ExtensionException(
                String.format("URLDecoder.decode UnsupportedEncodingException for file:%s", url.getFile()), uee);
        }

        String jarPath = rawJarPath.replaceFirst("!.*$", "").replaceFirst("^file:", "");
        try {
            return new JarFile(jarPath);
        } catch (IOException e) {
            throw new ExtensionException(String.format("new JarFile for jarPath:%s IOException.", jarPath), e);
        }
    }

    public interface ClassFilter {
        boolean accept(Class c);
    }

}
