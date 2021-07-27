package com.extp.framework.core.manager;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import com.extp.framework.core.classfinder.ClassScanner;
import com.extp.framework.core.classfinder.ClassScannerImpl;
import com.extp.framework.model.Plugin;

/**
 * 实现的插件管理
 *
 * @author xuan
 * @since 2021/2/26
 */
public class PluginManager {

    private ClassScanner classScanner = new ClassScannerImpl();

    private static final PluginManager INSTANCE = new PluginManager();

    private Set<Class> pluginClassSet = new HashSet<>();

    public static PluginManager getInstance() {
        return INSTANCE;
    }

    public void register(Set<String> packageNameSet) {
        pluginClassSet.addAll(getPluginClass(packageNameSet));
    }

    public Set<Class> getPluginClassSet() {
        return pluginClassSet;
    }

    private Set<Class> getPluginClass(Set<String> packageNameSet) {
        return classScanner.getAllClasses((c) -> {
            int modifiers = c.getModifiers();

            //不需要抽象类
            if (Modifier.isAbstract(modifiers)) {
                return false;
            }
            //不需要接口
            if (Modifier.isInterface(modifiers)) {
                return false;
            }

            //带Plugin注解的
            return null != c.getAnnotation(Plugin.class);
        }, packageNameSet);
    }

}
