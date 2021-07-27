package com.extp.framework.core.classfinder;

import java.util.Set;

/**
 * @author xuan
 * @since 2021/1/25
 */
public interface ClassScanner {

    Set<Class> getAllClasses(ClassFilter filter, Set<String> packageNameSet);
}
