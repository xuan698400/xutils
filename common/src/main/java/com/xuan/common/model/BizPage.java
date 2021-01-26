package com.xuan.common.model;

/**
 * @author xuan
 * @since 2020/3/14
 */

public class BizPage {
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页号，注意从1开始
     */
    private int pageNo;
    /**
     * 每页大小
     */
    private int pageSize = DEFAULT_PAGE_SIZE;
    /**
     * 总记录数
     */
    private int totalNum;

    public BizPage(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        if (this.pageNo < 1) {
            this.pageNo = 1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (this.pageSize < 1) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getFirst() {
        if (getPageNo() < 1 || getPageSize() < 1) {
            return 0;
        }
        return (this.pageNo - 1) * this.pageSize;
    }

    public int getLast() {
        return getFirst() + getPageSize() - 1;
    }

}
