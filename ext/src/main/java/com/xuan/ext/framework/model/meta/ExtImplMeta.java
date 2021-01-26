package com.xuan.ext.framework.model.meta;

/**
 * @author xuan
 * @since 2021/1/26
 */
public class ExtImplMeta {
    
    private Class extImplClass;

    private Object extImplInstance;

    public Class getExtImplClass() {
        return extImplClass;
    }

    public void setExtImplClass(Class extImplClass) {
        this.extImplClass = extImplClass;
    }

    public Object getExtImplInstance() {
        return extImplInstance;
    }

    public void setExtImplInstance(Object extImplInstance) {
        this.extImplInstance = extImplInstance;
    }
}
