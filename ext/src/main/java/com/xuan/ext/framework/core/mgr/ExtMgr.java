package com.xuan.ext.framework.core.mgr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.xuan.ext.framework.core.classfind.ClassFinder;
import com.xuan.ext.framework.core.classfind.ClassFinderImpl;
import com.xuan.ext.framework.core.common.Joiner;
import com.xuan.ext.framework.core.log.LogAdapter;
import com.xuan.ext.framework.core.log.LogAdapterFactory;
import com.xuan.ext.framework.model.annotation.Ext;
import com.xuan.ext.framework.model.meta.ExtMeta;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class ExtMgr {

    private final static LogAdapter LOG = LogAdapterFactory.getLogAdapter();

    private final static ExtMgr INSTANCE = new ExtMgr();

    private ClassFinder classFinder = new ClassFinderImpl();

    private Map<String, ExtMeta> extClassName2MetaMap = new HashMap<>();

    private ExtMgr() {

    }

    public static ExtMgr getInstance() {
        return INSTANCE;
    }

    public void register(String... packageNames) {
        LOG.warn(String.format("Start_Register_Ext. packageNames:%s", Joiner.join("|", packageNames)));

        Set<Class> extClassSet = classFinder.getExtClasses(packageNames);
        for (Class c : extClassSet) {
            Ext extAnnotation = (Ext)c.getAnnotation(Ext.class);
            extClassName2MetaMap.put(c.getName(), ExtMeta.of(extAnnotation.name(), extAnnotation.desc(), c));
        }

        Set<String> extInfoSet = new HashSet<>();
        for (ExtMeta extMeta : extClassName2MetaMap.values()) {
            String extInfo = String.format("extName=%s,extDesc=%s,className=%s",
                extMeta.getName(),
                extMeta.getDesc(),
                extMeta.getExtClass().getName());
            extInfoSet.add(extInfo);
        }

        LOG.warn(String.format("Finish_Register_Ext. extClassName:%s", Joiner.join("|", extInfoSet)));
    }

}
