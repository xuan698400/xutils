package com.xuan.ext.framework.core.classfind;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.xuan.ext.framework.core.common.ExtException;
import com.xuan.ext.framework.core.log.LogAdapter;
import com.xuan.ext.framework.core.log.LogAdapterFactory;
import com.xuan.ext.framework.model.annotation.Ext;
import com.xuan.ext.framework.model.annotation.ExtImpl;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class ClassFinderImpl implements ClassFinder {

    private final static LogAdapter LOG = LogAdapterFactory.getLogAdapter();

    @Override
    public Set<Class> getExtImlClasses(String... packageNames) {
        return getAllClasses((c) -> null != c.getAnnotation(ExtImpl.class), packageNames);
    }

    @Override
    public Set<Class> getExtClasses(String... packageNames) {
        return getAllClasses((c) -> null != c.getAnnotation(Ext.class), packageNames);
    }

    @Override
    public Set<Class> getAllClasses(ClassFilter filter, String... packageNames) {
        if (null == packageNames || packageNames.length == 0) {
            throw new RuntimeException("ClassFinderImpl: packageNames must not empty.");
        }

        Set<Class> allClassSet = new HashSet<>();
        for (String packageName : packageNames) {
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
            throw new ExtException("ClassFinderImpl_FindAllClassesUnderPackagePrefix_PackagePrefix_Is_Empty.");
        }

        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(packagePath);
        } catch (IOException ie) {
            throw new ExtException(
                "ClassFinderImpl_FindAllClassesUnderPackagePrefix_GetResources_IOException. packagePath:" + packagePath,
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
        } catch (UnsupportedEncodingException ex) {
            LOG.error("ClassFinderImpl_toFile_UnsupportedEncodingException. file:" + url.getFile(), ex);
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
        } catch (IOException ie) {
            throw new ExtException("ClassFinderImpl_toJarFile_IOException. ", ie);
        }
    }

    private URL fileToUrl(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException me) {
            throw new ExtException("ClassFinderImpl_fileToUrl_MalformedURLException. ", me);
        }
    }
}
