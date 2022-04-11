package com.xuan.mix.biz.category.model;

import java.util.Map;

/**
 * @author xuan
 * @since 2022/3/30
 */
public class CategoryAdd {
    /**
     * 类目名称【必填】
     */
    private String name;
    /**
     * 类目描述【可空】
     */
    private String desc;
    /**
     * 操作者【可空】
     */
    private String operator;
    /**
     * 父级类目【可空】，如果为空默认会设置成0表示根节点
     */
    private Long parent;
    /**
     * 扩展属性【可空】
     */
    private Map<String, String> feature;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Map<String, String> getFeature() {
        return feature;
    }

    public void setFeature(Map<String, String> feature) {
        this.feature = feature;
    }

}
