package com.xuan.xutils.domain.result;

import com.xuan.xutils.domain.BaseDO;

/**
 * 分页对象
 * <p>
 * Created by xuan on 17/8/2.
 */
public class Page extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int page;
    /**
     * 下一页
     */
    private int nextPage;
    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 总页数
     */
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
