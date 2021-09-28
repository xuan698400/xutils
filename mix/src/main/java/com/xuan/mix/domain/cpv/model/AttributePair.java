package com.xuan.mix.domain.cpv.model;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class AttributePair {

    private Attribute attribute;

    private AttributeValue attributeValue;

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public AttributeValue getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(AttributeValue attributeValue) {
        this.attributeValue = attributeValue;
    }

}
