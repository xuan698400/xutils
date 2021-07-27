package com.extp.framework.core.manager;

import java.util.HashSet;
import java.util.Set;

import com.extp.framework.core.classfinder.ClassScanner;
import com.extp.framework.core.classfinder.ClassScannerImpl;
import com.extp.framework.model.Ext;

/**
 * 定义的扩展点接口管理
 *
 * @author xuan
 * @since 2021/2/26
 */
public class ExtManager {
    private ClassScanner classScanner = new ClassScannerImpl();

    private static final ExtManager INSTANCE = new ExtManager();

    private Set<Class> extClassSet = new HashSet<>();

    public static ExtManager getInstance() {
        return INSTANCE;
    }

    public void register(Set<String> packageNameSet) {
        extClassSet.addAll(getExtClass(packageNameSet));
    }

    public Set<Class> getExtClassSet() {
        return extClassSet;
    }

    private Set<Class> getExtClass(Set<String> packageNameSet) {
        return classScanner.getAllClasses(c -> null != c.getAnnotation(Ext.class), packageNameSet);
    }

}
