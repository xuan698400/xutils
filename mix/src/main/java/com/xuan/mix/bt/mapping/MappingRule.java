package com.xuan.mix.bt.mapping;

import java.util.List;

/**
 * @author xuan
 * @since 2021/9/15
 */
public class MappingRule {

    private List<String> fromObjAttrLevel;

    private List<String> toObjAttrLevel;

    public List<String> getFromObjAttrLevel() {
        return fromObjAttrLevel;
    }

    public void setFromObjAttrLevel(List<String> fromObjAttrLevel) {
        this.fromObjAttrLevel = fromObjAttrLevel;
    }

    public List<String> getToObjAttrLevel() {
        return toObjAttrLevel;
    }

    public void setToObjAttrLevel(List<String> toObjAttrLevel) {
        this.toObjAttrLevel = toObjAttrLevel;
    }

}
