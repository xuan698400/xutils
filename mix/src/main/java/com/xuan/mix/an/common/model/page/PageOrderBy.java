package com.xuan.mix.an.common.model.page;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class PageOrderBy {
    private String fieldName;
    private Boolean isDesc;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Boolean getDesc() {
        return isDesc;
    }

    public void setDesc(Boolean desc) {
        isDesc = desc;
    }

}
