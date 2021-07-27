package com.extp.framework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.extp.framework.core.manager.ExtManager;
import com.extp.framework.core.manager.PluginManager;
import com.extp.framework.core.log.LogAdapter;
import com.extp.framework.core.log.LogAdapterFactory;
import com.extp.framework.model.Plugin;

/**
 * 扩展框架管理器
 *
 * @author xuan
 * @since 2021/2/26
 */
public class ExtpManager {
    private final static LogAdapter LOG = LogAdapterFactory.getLogAdapter();

    private static final ExtpManager INSTANCE = new ExtpManager();

    /**
     * 数据结构：最外层Map的key是扩展点的Class，里面层Map的key是bizCode，值是该扩展点的实现列表
     */
    private Map<Class, Map<String, List<Object>>> extMap = new HashMap<>();

    public static ExtpManager getInstance() {
        return INSTANCE;
    }

    public void init(ExtpConfig config) {
        //1. 注册扩展点定义（注解：com.extp.framework.model.Ext）
        ExtManager.getInstance().register(config.getScanExtPackageNameSet());

        //2. 注册插件实现（注解：com.extp.framework.model.Plugin）
        PluginManager.getInstance().register(config.getScanPluginPackageNameSet());

        //3. 解析插件实现的扩展点，和扩展点定义建立联系
        buildExtInstanceMap();
    }

    public <Ext> List<Ext> getExtPoints(Class<Ext> extPointsClass, String bizCode) {
        Map<String, List<Object>> bizCodeExtMap = extMap.get(extPointsClass);
        if (null == bizCodeExtMap) {
            return new ArrayList<>();
        }

        List<Object> extList = bizCodeExtMap.get(bizCode);
        if (null == extList) {
            return new ArrayList<>();
        }

        List<Ext> retList = new ArrayList<>();
        for (Object ext : extList) {
            retList.add((Ext)ext);
        }
        return retList;
    }

    private void buildExtInstanceMap() {
        //1. 获取所有扩展点定义
        Set<Class> extClassSet = ExtManager.getInstance().getExtClassSet();

        //2. 获取所有插件实现
        Set<Class> pluginClassSet = PluginManager.getInstance().getPluginClassSet();

        //3. 以bizCode分类聚合，找到所有扩展点定义对应的实现实例
        for (Class extClass : extClassSet) {
            Map<String, List<Object>> bizCodeExtMap = new HashMap<>();
            for (Class pluginClass : pluginClassSet) {
                buildPlugin(extClass, pluginClass, bizCodeExtMap);
            }
            extMap.put(extClass, bizCodeExtMap);
        }
    }

    /**
     * 指定扩展点，扫描插件，找出对该扩展点的实现
     *
     * @param extClass      指定扩展点
     * @param pluginClass   需要扫描的插件
     * @param bizCodeExtMap 找出对扩展点的实现，以业务身份方式关联
     */
    private void buildPlugin(
        Class<?> extClass,
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
                Object ext = createExtPointsInstance(pluginClass, extClass);
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
                        LOG.error(String.format(
                            "ExtpManager_createExtPointsInstance_InvocationTargetException. pluginClass:%s, "
                                + "extClass:%s, method:%s",
                            pluginClass.getName(), extClass.getName(), method.getName()), e);
                    }
                }
            }

        } catch (InstantiationException e) {
            LOG.error(String.format(
                "ExtpManager_createExtPointsInstance_InstantiationException. pluginClass:%s, "
                    + "extClass:%s",
                pluginClass.getName(), extClass.getName()), e);
        } catch (IllegalAccessException e) {
            LOG.error(String.format(
                "ExtpManager_createExtPointsInstance_IllegalAccessException. pluginClass:%s, "
                    + "extClass:%s",
                pluginClass.getName(), extClass.getName()), e);
        }
        return null;
    }

}
