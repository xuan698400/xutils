package com.xuan.dao.common;

import java.util.List;


/**
 * 一些分页场景可以用这个模型来承载
 *
 * @author xuan
 * @since 2021/12/19
 */
public class PageData<T> {

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
