package com.xuan.moho.framework.extension.framework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xuan.moho.framework.extension.framework.model.Extension;
import com.xuan.moho.framework.extension.framework.model.Plugin;

/**
 * 扩展框架管理器
 *
 * @author xuan
 * @since 2021/2/26
 */
public class ExtensionManager {

    /**
     * 所有扩展点接口的Class类
     */
    private Set<Class> extensionClasses = new HashSet<>();
    /**
     * 所有插件实现类
     */
    private Set<Class> pluginClasses = new HashSet<>();
    /**
     * 数据结构：最外层Map的key是扩展点的Class，里面层Map的key是bizCode，值是该扩展点的实现列表
     */
    private Map<Class, Map<String, List<Object>>> extensionInstanceMap = new HashMap<>();

    private static final ExtensionManager INSTANCE = new ExtensionManager();

    private ExtensionManager() {

    }

    public static ExtensionManager getInstance() {
        return INSTANCE;
    }

    public void init(ExtensionConfig config) {
        //1. 扩展点注册，参考注解：com.xuan.mix.spi.framework.Extension
        registerExtensions(config.getExtensionPackages());

        //2. 插件注册，参考注解：com.xuan.mix.extension.framework.model.Plugin
        registerPlugins(config.getPluginPackages());

        //3. 解析插件实现的扩展点，和扩展点定义建立联系
        buildExtInstanceMap();
    }

    public <Extension> List<Extension> getExtensions(Class<Extension> extensionClass, String bizCode) {
        Map<String, List<Object>> bizCode2ExtensionsMap = extensionInstanceMap.get(extensionClass);
        if (null == bizCode2ExtensionsMap) {
            return new ArrayList<>();
        }

        List<Object> extList = bizCode2ExtensionsMap.get(bizCode);
        if (null == extList) {
            return new ArrayList<>();
        }

        List<Extension> retList = new ArrayList<>();
        for (Object ext : extList) {
            retList.add((Extension)ext);
        }
        return retList;
    }

    private void buildExtInstanceMap() {
        for (Class extensionClass : extensionClasses) {
            Map<String, List<Object>> bizCodeExtMap = new HashMap<>();
            for (Class pluginClass : pluginClasses) {
                buildPlugin(extensionClass, pluginClass, bizCodeExtMap);
            }
            extensionInstanceMap.put(extensionClass, bizCodeExtMap);
        }
    }

    /**
     * 指定扩展点，扫描插件，找出对该扩展点的实现
     *
     * @param extensionClass 指定扩展点
     * @param pluginClass    需要扫描的插件
     * @param bizCodeExtMap  找出对扩展点的实现，以业务身份方式关联
     */
    private void buildPlugin(
        Class<?> extensionClass,
        Class<?> pluginClass,
        Map<String, List<Object>> bizCodeExtMap) {

        Plugin[] pluginAnnotations = pluginClass.getAnnotationsByType(Plugin.class);
        if (null == pluginAnnotations || pluginAnnotations.length == 0) {
            return;
        }

        for (Plugin pluginAnnotation : pluginAnnotations) {
            String[] bizCodes = pluginAnnotation.supportBizCodes();
            if (bizCodes.length == 0) {
                continue;
            }

            for (String bizCode : bizCodes) {
                Object ext = createExtPointsInstance(pluginClass, extensionClass);
                if (null == ext) {
                    continue;
                }
                List<Object> extList = bizCodeExtMap.computeIfAbsent(bizCode, k -> new ArrayList<>());
                extList.add(ext);
            }
        }
    }

    private Object createExtPointsInstance(Class<?> pluginClass, Class<?> extClass) {

        Object pluginInstance;
        try {
            pluginInstance = pluginClass.newInstance();

            Method[] methods = pluginClass.getDeclaredMethods();

            for (Method method : methods) {
                //表示返回的是该扩展点的实例
                if (extClass.isAssignableFrom(method.getReturnType())) {
                    try {
                        return method.invoke(pluginInstance);
                    } catch (InvocationTargetException e) {
                        LogUtil.error(String.format(
                            "method.invoke InvocationTargetException. pluginClass:%s, "
                                + "extClass:%s, method:%s",
                            pluginClass.getName(), extClass.getName(), method.getName()), e);
                    }
                }
            }

        } catch (InstantiationException e) {
            LogUtil.error(String.format(
                "pluginClass.newInstance InstantiationException. pluginClass:%s, "
                    + "extClass:%s",
                pluginClass.getName(), extClass.getName()), e);
        } catch (IllegalAccessException e) {
            LogUtil.error(String.format(
                "pluginClass.newInstance IllegalAccessException. pluginClass:%s, "
                    + "extClass:%s",
                pluginClass.getName(), extClass.getName()), e);
        }
        return null;
    }

    private void registerExtensions(Set<String> extensionPackages) {
        Set<Class> temp = ClassUtil.getAllClasses(c -> null != c.getAnnotation(Extension.class),
            extensionPackages);
        if (null == temp || temp.isEmpty()) {
            return;
        }
        extensionClasses.addAll(temp);
    }

    private void registerPlugins(Set<String> pluginPackages) {
        Set<Class> temp = ClassUtil.getAllClasses((c) -> {
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
        }, pluginPackages);
        if (null == temp || temp.isEmpty()) {
            return;
        }
        pluginClasses.addAll(temp);
    }

}
