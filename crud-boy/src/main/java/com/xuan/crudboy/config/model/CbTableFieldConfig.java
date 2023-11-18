package com.xuan.crudboy.config.model;

/**
 * @author xuan
 * @since 2023/11/5
 */
public class CbTableFieldConfig {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段描述
     */
    private String desc;

    /**
     * 字段类型，参考：com.xuan.crudboy.config.model.CbTableFieldTypeEnum
     */
    private String type;

    /**
     * 最大长度
     */
    private String maxLength;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 是否是主键
     */
    private Boolean primary;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
}
