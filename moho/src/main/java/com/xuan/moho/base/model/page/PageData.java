package com.xuan.moho.base.model.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long totalCount;

    private List<T> data;

    public static <T> PageData<T> of(List<T> data, Long totalCount) {
        PageData<T> pageData = new PageData<>();
        pageData.setData(data);
        pageData.setTotalCount(totalCount);
        return pageData;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
