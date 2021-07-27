package com.extp.framework.core.classfinder;

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

import com.extp.framework.core.common.ExtException;
import com.extp.framework.core.log.LogAdapter;
import com.extp.framework.core.log.LogAdapterFactory;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class ClassScannerImpl implements ClassScanner {

    private final static LogAdapter LOG = LogAdapterFactory.getLogAdapter();

    @Override
    public Set<Class> getAllClasses(ClassFilter filter, Set<String> packageNameSet) {
        if (null == packageNameSet || packageNameSet.isEmpty()) {
            throw new RuntimeException("ClassScannerImpl_getAllClasses_packageNameSet_is_empty.");
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

    private Set<Class> doGetClassesUnderPackage(String packageName) {

        if (null == packageName || packageName.trim().isEmpty()) {
            throw new ExtException("ClassFinderImpl_doGetClassesUnderPackage_packageName_is_empty.");
        }

        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(packagePath);
        } catch (IOException ie) {
            throw new ExtException(
                "ClassFinderImpl_doGetClassesUnderPackage_getResources_IOException. packagePath:" + packagePath,
                ie);
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
    private void fetchClassFromDirectory(File dir, String packageName, Set<Class> classSet) {

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
                } catch (ClassNotFoundException cnfe) {
                    throw new ExtException(
                        "ClassFinderImpl_fetchClassFromDirectory_ClassNotFoundException. className:" + className, cnfe);
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
    private void fetchClassFromJar(URL jarUrl, String packageName, Set<Class> classSet) {
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
                try {
                    String className = jarEntry.getName().replaceFirst("\\.class$", "").replace("/", ".");
                    classSet.add(Class.forName(className, false, classLoader));
                } catch (Throwable e) {
                    LOG.error("ClassFinderImpl_FetchClassFromJar_Throwable. className:" + jarEntry.getName(), e);
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
            LOG.error("ClassFinderImpl_toFile_UnsupportedEncodingException. file:" + url.getFile(), e);
        }
        return new File(url.toString());
    }

    private static JarFile toJarFile(final URL url) {

        String rawJarPath;
        try {
            rawJarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new ExtException("ClassFinderImpl_toJarFile_UnsupportedEncodingException.", uee);
        }

        String jarPath = rawJarPath.replaceFirst("!.*$", "").replaceFirst("^file:", "");
        try {
            return new JarFile(jarPath);
        } catch (IOException e) {
            throw new ExtException("ClassFinderImpl_toJarFile_IOException. ", e);
        }
    }

}
