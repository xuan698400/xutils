package com.xuan.ext.framework.model.meta;

/**
 * @author xuan
 * @since 2021/1/24
 */
public class ExtMeta {

    private String name;

    private String desc;

    private Class extClass;

    public static ExtMeta of(String name, String desc, Class extClass) {
        ExtMeta extMeta = new ExtMeta();
        extMeta.setName(name);
        extMeta.setDesc(desc);
        extMeta.setExtClass(extClass);
        return extMeta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class getExtClass() {
        return extClass;
    }

    public void setExtClass(Class extClass) {
        this.extClass = extClass;
    }

}
