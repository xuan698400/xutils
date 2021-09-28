package com.xuan.mix.domain.cpv.model;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class Attribute {

    private Long id;

    private String name;
    /**
     * 所属于类目
     */
    private Category category;
    /**
     * 属性类型
     */
    private AttributeType attributeType;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

}
