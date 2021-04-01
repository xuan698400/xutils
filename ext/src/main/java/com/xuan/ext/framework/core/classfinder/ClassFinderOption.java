package com.xuan.ext.framework.core.classfinder;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class ClassFinderOption {

    private boolean includeAbstract = false;
    private boolean includeInterface = false;

    public boolean isIncludeAbstract() {
        return includeAbstract;
    }

    public void setIncludeAbstract(boolean includeAbstract) {
        this.includeAbstract = includeAbstract;
    }

    public boolean isIncludeInterface() {
        return includeInterface;
    }

    public void setIncludeInterface(boolean includeInterface) {
        this.includeInterface = includeInterface;
    }
    
}
