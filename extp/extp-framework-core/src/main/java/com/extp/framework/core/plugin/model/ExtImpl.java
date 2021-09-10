package com.extp.framework.core.plugin.model;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class ExtImpl {
    private String ext;
    private String impl;

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getImpl() {
        return impl;
    }

    public void setImpl(String impl) {
        this.impl = impl;
    }

    @Override
    public String toString() {
        return "ExtImpl{" +
            "ext='" + ext + '\'' +
            ", java='" + impl + '\'' +
            '}';
    }
    
}
