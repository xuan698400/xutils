package com.xuan.xutils.sql.orm.model.page;

import java.io.Serializable;

/**
 * 分页排序模型
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
    private boolean isDesc = true;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isDesc() {
        return isDesc;
    }

    public void setDesc(boolean desc) {
        isDesc = desc;
    }

}
