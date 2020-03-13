package com.xuan.common.model;

/**
 * @author xuan
 * @since 2020/3/14
 */

public class BwPageQuery {

    /**
     * 第几页，下标从1开始
     */
    private int pageNo;
    /**
     * 每页大小
     */
    private int pageSize;

    public BwPageQuery(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
