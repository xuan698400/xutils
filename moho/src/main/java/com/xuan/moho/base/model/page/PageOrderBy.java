package com.xuan.moho.base.model.page;

import java.io.Serializable;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class PageOrderBy implements Serializable {
    private static final long serialVersionUID = 1L;

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
