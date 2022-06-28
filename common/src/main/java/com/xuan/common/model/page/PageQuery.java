package com.xuan.common.model.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean needTotalCount = false;
    private int pageSize = 10;
    private int pageIndex = 1;
    private List<PageOrderBy> orderByList;

    public Integer getOffset() {
        pageSize = pageSize < 0 ? 10 : pageSize;
        pageIndex = pageIndex < 0 ? 1 : pageIndex;
        return (pageIndex - 1) * pageSize;
    }

    public boolean isNeedTotalCount() {
        return needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<PageOrderBy> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<PageOrderBy> orderByList) {
        this.orderByList = orderByList;
    }

}
