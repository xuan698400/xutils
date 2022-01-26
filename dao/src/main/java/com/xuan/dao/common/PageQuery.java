package com.xuan.dao.common;

/**
 * @author xuan
 * @since 2021/12/19
 */
public class PageQuery {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_INDEX = 1;
    private static final boolean DEFAULT_ORDER_DIRECTION = true;
    private static final String DEFAULT_ORDER_BY = "gmt_modified";
    private static final Boolean DEFAULT_NEED_TOTAL_COUNT = false;

    private Integer pageSize;
    private Integer pageIndex;
    private String orderBy;
    private Boolean isDesc = true;
    private Boolean needTotalCount = true;

    public String getOrderBy() {
        if (null == orderBy) {
            return DEFAULT_ORDER_BY;
        }
        return orderBy;
    }

    public Boolean isDesc() {
        if (null == isDesc) {
            return DEFAULT_ORDER_DIRECTION;
        }
        return isDesc;
    }

    public Integer getOffset() {
        if (null == pageIndex || pageIndex < 1) {
            pageIndex = DEFAULT_PAGE_INDEX;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return (pageIndex - 1) * pageSize;
    }

    public Integer getPageSize() {
        if (null == pageSize || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public Boolean needTotalCount() {
        if (null == needTotalCount) {
            return DEFAULT_NEED_TOTAL_COUNT;
        }
        return needTotalCount;
    }

}
