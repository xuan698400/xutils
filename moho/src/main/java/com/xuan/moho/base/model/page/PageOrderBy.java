package com.xuan.moho.base.model.page;

import java.io.Serializable;

/**
 * 排序模型
 *
 * @author xuan
 * @since 2022/6/1
 */
public class PageOrderBy implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 排序字段
     */
    private String fieldName;

    /**
     * 是否将序
     */
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
