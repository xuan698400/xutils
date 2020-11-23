package com.xuan.mix.spi.framework;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author xuan
 * @since 2020/11/13
 */
public class ExtensionFinder {

    private ExtensionLogger log;
    private String packagePrefix;
    private boolean includeAbstract = false;
    private boolean includeInterface = false;

    private Set<Class> extensionClassSet;

    public ExtensionFinder(String packagePrefix, ExtensionLogger log) {
        this.packagePrefix = packagePrefix;
        this.log = log;
    }

    public ExtensionFinder(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public <T> void scanExtension(final Class<T> extensionPointClass) {
        long start = System.currentTimeMillis();

        if (packagePrefix.isEmpty()) {
            throw new RuntimeException("ExtensionFinder: packagePrefix must not empty.");
        }

        Set<Class> tempClassSet;
        try {
            tempClassSet = findClassesUnderPackagePrefix(packagePrefix);
            if (null == tempClassSet || tempClassSet.isEmpty()) {
                extensionClassSet = new HashSet<>();
                return;
            }

            tempClassSet.removeIf((clazz) -> !extensionPointClass.isAssignableFrom(clazz));
        } catch (Exception e) {
            throw new RuntimeException(
                "ExtensionFinder: Find extension under packagePrefix[" + packagePrefix + "] Exception.", e);
        }

        if (!includeAbstract) {
            tempClassSet.removeIf((clazz) -> Modifier.isAbstract(clazz.getModifiers()));
        }
        if (!includeInterface) {
            tempClassSet.removeIf((clazz) -> Modifier.isInterface(clazz.getModifiers()));
        }
        extensionClassSet = tempClassSet;
        long elapsed = System.currentTimeMillis() - start;
        logWarn("Find extension under packagePrefix[" + packagePrefix + "]. elapsed:" + elapsed);
    }

    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix == null ? "" : packagePrefix;
    }

    public void setIncludeAbstract(boolean includeAbstract) {
        this.includeAbstract = includeAbstract;
    }

    public void setIncludeInterface(boolean includeInterface) {
        this.includeInterface = includeInterface;
    }

    private Set<Class> findClassesUnderPackagePrefix(final String packagePrefix)
        throws ClassNotFoundException, IOException {

        String packagePath = packagePrefix.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(packagePath);

        Set<Class> tempClassSet = new HashSet<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (isJarFile(resource)) {
                // This should not be executed if the package prefix is empty.
                addClassesFromJarFile(resource, packagePrefix, tempClassSet);
            } else {
                File file = toFile(resource);
                if (file.exists() && file.isDirectory()) {
                    addClassesFromDirectory(file, packagePrefix, tempClassSet);
                }
            }
        }

        if (packagePrefix.isEmpty()) {
            String[] classPaths = System.getProperty("java.class.path").split(";");
            Set<String> classPathSet = new HashSet<>(Arrays.asList(classPaths));
            for (String string : classPathSet) {
                File file = new File(string);
                if (file.exists() && file.isFile() && file.getName().endsWith(".jar")) {
                    URL jarUrl = fileToUrl(file);
                    if (jarUrl != null) {
                        addClassesFromJarFile(jarUrl, packagePath, tempClassSet);
                    }
                }
            }
        }

        return tempClassSet;
    }

    private void addClassesFromDirectory(File directory, String packageName, Set<Class> tempClassSet)
        throws ClassNotFoundException {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                String filename = file.getName();
                if (file.isDirectory()) {
                    addClassesFromDirectory(file, packageName + (packageName.isEmpty() ? "" : ".") + filename,
                        tempClassSet);
                } else if (filename.endsWith(".class")) {
                    tempClassSet.add(Class.forName(packageName + (packageName.isEmpty() ? "" : ".") + filename
                        .substring(0, filename.length() - 6)));
                }
            }
        }
    }

    private void addClassesFromJarFile(URL url, String packageName, Set<Class> tempClassSet) {
        String packagePathFilter = packageName.replace('.', '/');
        Enumeration<JarEntry> allEntries = toJarFile(url).entries();
        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        while (allEntries.hasMoreElements()) {
            JarEntry jarEntry = allEntries.nextElement();
            String entryName = jarEntry.getName();
            if (!jarEntry.isDirectory() && entryName.startsWith(packagePathFilter) && entryName.endsWith(".class")) {
                try {
                    String className = jarEntry.getName().replaceFirst("\\.class$", "").replace("/", ".");
                    tempClassSet.add(Class.forName(className, false, contextClassLoader));
                } catch (Throwable t) {
                    logError("Cannot get Class for name:" + jarEntry.getName(), t);
                }
            }
        }
    }

    private boolean isJarFile(URL url) {
        return url.toString().startsWith("jar:file:");
    }

    private File toFile(URL url) {
        try {
            String filepath = URLDecoder.decode(url.getFile(), "UTF-8");
            return new File(filepath);
        } catch (UnsupportedEncodingException uee) {
            logError("URL[" + url.getFile() + "] decode using UTF-8 UnsupportedEncodingException.", uee);
        }
        logWarn("URL[" + url.getFile() + "] is not decoded.");
        return new File(url.toString());
    }

    private JarFile toJarFile(final URL url) {

        String rawJarPath = null;
        try {
            rawJarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            logError("URL[" + url.getFile() + "] decode using UTF-8 UnsupportedEncodingException.", uee);
        }

        if (null == rawJarPath) {
            logWarn("Null JarFile is returned. Cause url[" + url.getFile() + "] decode UTF-8 fail.");
            return null;
        }

        String jarPath = rawJarPath.replaceFirst("!.*$", "").replaceFirst("^file:", "");

        try {
            return new JarFile(jarPath);
        } catch (IOException ie) {
            logError("Create JarFile[" + jarPath + "] IOException", ie);
        }

        logWarn("Null JarFile is returned. Cause Create JarFile[" + jarPath + "] fail.");
        return null;
    }

    private URL fileToUrl(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException("file to URL MalformedURLException. fileName:" + file.getName(), e);
        }
    }

    private void logError(String msg, Throwable throwable) {
        if (null == log) {
            return;
        }
        log.error("ExtensionFinder:" + msg, throwable);
    }

    private void logWarn(String msg) {
        if (null == log) {
            return;
        }
        log.warn("ExtensionFinder:" + msg);
    }

}
