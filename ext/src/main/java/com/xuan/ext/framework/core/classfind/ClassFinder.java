package com.xuan.ext.framework.core.classfind;

import java.util.Set;

/**
 * @author xuan
 * @since 2021/1/25
 */
public interface ClassFinder {

    Set<Class> getAllClasses(ClassFilter filter, String... packageNames);

    Set<Class> getExtClasses(String... packageNames);

    Set<Class> getExtImlClasses(String... packageNames);
}
