package com.xuan.mix.domain.cpv.model;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class AttributeValue {

    private Long id;

    private String name;

    /**
     * 所属属性
     */
    private Attribute attribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
