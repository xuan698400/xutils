package com.xuan.spi2.framework;

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
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuan
 * @since 2020/11/13
 */
public class SubClassFinder {
    private static final Logger LOG = LoggerFactory.getLogger(SubClassFinder.class);

    private String packagePrefix;
    private static boolean logging = false;
    private boolean includeAbstract = false;
    private boolean includeInterface = false;

    private Set<Class> allClassSet;

    public SubClassFinder(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public <T> Set<Class<? extends T>> getSubClassesOf(final Class<T> fatherClazz) {
        long start = System.currentTimeMillis();

        if (packagePrefix.isEmpty()) {
            throw new RuntimeException("SubClassFinder: packagePrefix must not empty.");
        }

        final Set<Class<? extends T>> subClazzSet = new HashSet<>();
        try {
            Set<Class> allClazzSet = getClassesUnderPackagePrefix(packagePrefix);
            for (Class clazz : allClazzSet) {
                if (fatherClazz.isAssignableFrom(clazz)) {
                    subClazzSet.add(clazz);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("SubClassFinder: getClassesUnderPackagePrefix exception.", e);
        }

        if (!includeAbstract) {
            for (Iterator<Class<? extends T>> it = subClazzSet.iterator(); it.hasNext(); ) {
                int modifiers = it.next().getModifiers();
                if (Modifier.isAbstract(modifiers)) {
                    it.remove();
                }
            }
        }
        if (!includeInterface) {
            for (Iterator<Class<? extends T>> it = subClazzSet.iterator(); it.hasNext(); ) {
                int modifiers = it.next().getModifiers();
                if (Modifier.isInterface(modifiers)) {
                    it.remove();
                }
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(String.format("SubClassFinder: look up subclasses of %s, time %s", fatherClazz.getName(),
            (System.currentTimeMillis() - start)));

        return subClazzSet;
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

    private Set<Class> getClassesUnderPackagePrefix(final String packagePrefix)
        throws ClassNotFoundException, IOException {

        String packagePath = packagePrefix.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(packagePath);

        allClassSet = new HashSet<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (isJarFile(resource)) {
                // This should not be executed if the package prefix is empty.
                addClassesFromJarFile(resource, packagePrefix);
            } else {
                File file = toFile(resource);
                if (file.exists() && file.isDirectory()) {
                    addClassesFromDirectory(file, packagePrefix);
                }
            }
        }

        if (packagePrefix.isEmpty()) {
            String[] classPaths = System.getProperty("java.class.path").split(";");
            Set<String> classPathSet = new HashSet<String>(Arrays.asList(classPaths));
            for (String string : classPathSet) {
                File file = new File(string);
                if (file.exists() && file.isFile() && file.getName().endsWith(".jar")) {
                    URL jarUrl = fileToUrl(file);
                    if (jarUrl != null) {
                        addClassesFromJarFile(jarUrl, packagePath);
                    }
                }
            }
        }

        return allClassSet;
    }

    private void addClassesFromDirectory(File directory, String packageName) throws ClassNotFoundException {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                String filename = file.getName();
                if (file.isDirectory()) {
                    addClassesFromDirectory(file, packageName + (packageName.isEmpty() ? "" : ".") + filename);
                } else if (filename.endsWith(".class")) {
                    allClassSet.add(Class.forName(packageName + (packageName.isEmpty() ? "" : ".") + filename
                        .substring(0, filename.length() - 6)));
                }
            }
        }
    }

    private void addClassesFromJarFile(URL url, String packageName) {
        String packagePathFilter = packageName.replace('.', '/');
        Enumeration<JarEntry> allEntries = toJarFile(url).entries();
        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        while (allEntries.hasMoreElements()) {
            JarEntry jarEntry = allEntries.nextElement();
            String entryName = jarEntry.getName();
            if (!jarEntry.isDirectory() && entryName.startsWith(packagePathFilter) && entryName.endsWith(".class")) {
                try {
                    String className = jarEntry.getName().replaceFirst("\\.class$", "").replace("/", ".");
                    allClassSet.add(Class.forName(className, false, contextClassLoader));
                } catch (Throwable ex) {
                    if (logging) {
                        LOG.error("Cannot get Class for name : " + jarEntry.getName(), ex);
                    }
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
            if (logging) {
                LOG.error("Unable to decode jar file location using UTF-8.", ex);
            }
        }
        if (logging) {
            LOG.warn("URL is not decoded : " + url.toString());
        }
        return new File(url.toString());
    }

    /**
     * Get a jar file object from given resource URL. The bits after !/ in jar URL is removed before processing it to
     * jar file object.
     *
     * @param url
     * @return
     */
    private static JarFile toJarFile(final URL url) {
        try {
            String rawJarPath = URLDecoder.decode(url.getFile(), "UTF-8");
            String jarPath = rawJarPath.replaceFirst("!.*$", "").replaceFirst("^file:", "");
            try {
                JarFile jarFile = new JarFile(jarPath);
                return jarFile;
            } catch (IOException ex) {
                if (logging) {
                    LOG.error("Cannot create JarFile object for path : " + jarPath, ex);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            if (logging) {
                LOG.error("Unable to decode jar file location using UTF-8.", ex);
            }
        }
        if (logging) {
            LOG.info("Null JarFile is returned.");
        }
        return null;
    }

    private URL fileToUrl(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException("SubClassFinder: file to URL MalformedURLException.", e);
        }
    }

}
