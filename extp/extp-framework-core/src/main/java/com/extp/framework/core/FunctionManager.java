package com.extp.framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extp.framework.core.log.LogAdapter;
import com.extp.framework.core.log.LogAdapterFactory;
import com.extp.framework.core.plugin.PluginManager;
import com.extp.framework.core.plugin.model.BizCode;
import com.extp.framework.core.plugin.model.ExtImpl;
import com.extp.framework.core.plugin.model.Plugin;

/**
 * @author xuan
 * @since 2021/8/12
 */
public class FunctionManager {

    private final static LogAdapter LOG = LogAdapterFactory.getLogAdapter();

    private static final FunctionManager INSTANCE = new FunctionManager();

    /**
     * 数据结构：最外层Map的key是扩展点的Class的类全路径，里面层Map的key是bizCode，值是该扩展点的实现列表
     */
    private Map<String, Map<String, List<Object>>> extMap = new HashMap<>();

    public static FunctionManager getInstance() {
        return INSTANCE;
    }

    public void init() {
        List<Plugin> pluginList = PluginManager.getInstance().getAllPlugin();
        if (null == pluginList || pluginList.isEmpty()) {
            return;
        }
        for (Plugin plugin : pluginList) {
            registerPlugin(plugin);
        }
    }

    public void registerPlugin(Plugin plugin) {
        if (null == plugin.getBizCodeList() || null == plugin.getExtImplList()) {
            return;
        }
        for (ExtImpl extImpl : plugin.getExtImplList()) {
            Map<String, List<Object>> bizCode2InstancesMap = extMap.computeIfAbsent(extImpl.getExt(),
                k -> new HashMap<>());
            for (BizCode bizCode : plugin.getBizCodeList()) {
                List<Object> instanceList = bizCode2InstancesMap.computeIfAbsent(bizCode.getValue(),
                    k -> new ArrayList<>());
                Object instance = createInstance(extImpl);
                if (null != instance) {
                    instanceList.add(instance);
                }
            }
        }
    }

    public Map<String, Map<String, List<Object>>> getExtMap() {
        return extMap;
    }

    public <Ext> List<Ext> getExt(Class<Ext> extPointsClass, String bizCode) {
        Map<String, List<Object>> bizCode2InstancesMap = extMap.get(extPointsClass.getName());
        if (null == bizCode2InstancesMap) {
            return new ArrayList<>();
        }

        List<Object> instanceList = bizCode2InstancesMap.get(bizCode);
        if (null == instanceList) {
            return new ArrayList<>();
        }

        List<Ext> retList = new ArrayList<>();
        for (Object instance : instanceList) {
            retList.add((Ext)instance);
        }
        return retList;
    }

    private Object createInstance(ExtImpl extImpl) {
        Class clazz = loadClass(extImpl.getImpl());
        if (null == clazz) {
            return null;
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException ie) {
            LOG.error("FunctionManager_createInstance_InstantiationException. extImpl:" + extImpl.toString());
        } catch (IllegalAccessException iae) {
            LOG.error("FunctionManager_loadClass_ClassNotFoundException. extImpl:" + extImpl.toString());
        }
        return null;
    }

    private Class loadClass(String impl) {
        try {
            return Class.forName(impl);
        } catch (ClassNotFoundException e) {
            LOG.error("FunctionManager_loadClass_ClassNotFoundException. java:" + impl);
        }
        return null;
    }

}
