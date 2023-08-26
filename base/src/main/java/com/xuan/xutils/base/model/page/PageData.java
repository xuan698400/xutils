package com.xuan.xutils.base.model.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 *
 * @author xuan
 * @since 2022/6/1
 */
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 数据列表
     */
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
