package com.xuan.dbconsole.domain.model;

import java.util.List;

/**
 * @author xuan
 * @since 2022/12/8
 */
public class PageResult<T> {

    private List<T> data;

    private Long totalCount;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}
