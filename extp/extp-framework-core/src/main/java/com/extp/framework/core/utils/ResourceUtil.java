package com.extp.framework.core.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class ResourceUtil {

    public static List<URL> getResources(String... resourceNames) {
        List<URL> urlList = new LinkedList<>();

        for (String resourceName : resourceNames) {
            boolean found;
            // 先尝试从线上上下文中的ClassLoader加载资源
            found = collectResources(urlList, resourceName, Thread.currentThread().getContextClassLoader(), false);

            // 如果没找到，试着从装入自己的ClassLoader中查找
            if (!found) {
                collectResources(urlList, resourceName, ResourceUtil.class.getClassLoader(), false);
            }

            // 还是找不到，就在系统ClassLoader中查找
            if (!found) {
                collectResources(urlList, resourceName, null, true);
            }
        }
        // 返回不重复的列表。
        return filterRepeat(urlList);

    }

    private static List<URL> filterRepeat(List<URL> urList) {
        if (null == urList || urList.isEmpty()) {
            return urList;
        }

        Set<URL> noRepeatSet = new HashSet<>(urList.size());
        Iterator<URL> iterator = urList.iterator();
        while (iterator.hasNext()) {
            URL url = iterator.next();
            if (noRepeatSet.contains(url)) {
                iterator.remove();
            } else {
                noRepeatSet.add(url);
            }
        }
        return urList;
    }

    private static boolean collectResources(List<URL> urlList, String resourceName, ClassLoader classLoader,
        boolean sysClassLoader) {

        if (null == resourceName) {
            return false;
        }

        Enumeration<URL> i = null;
        try {
            if (null != classLoader) {
                i = classLoader.getResources(resourceName);
            } else if (sysClassLoader) {
                i = ClassLoader.getSystemResources(resourceName);
            }
        } catch (IOException e) {
            //Ignore
        }

        if (null != i && i.hasMoreElements()) {
            while (i.hasMoreElements()) {
                urlList.add(i.nextElement());
            }

            return true;
        }
        return false;
    }

}
