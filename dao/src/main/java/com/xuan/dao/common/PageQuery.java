package com.xuan.dao.common;

/**
 * @author xuan
 * @since 2021/12/19
 */
public class PageQuery {
    private Integer pageSize;
    private Integer pageIndex;
    private String orderBy;
    private Boolean isDesc;
    private Boolean needTotalCount;

    private PageQuery() {

    }

    public static PageQuery of(Integer pageSize, Integer pageIndex, Boolean needTotalCount) {
        return PageQuery.of(pageSize, pageIndex, null, null, needTotalCount);
    }

    public static PageQuery of(Integer pageSize, Integer pageIndex) {
        return PageQuery.of(pageSize, pageIndex, null, null, null);
    }

    public static PageQuery of(Integer pageSize, Integer pageIndex, String orderBy, Boolean isDesc,
        Boolean needTotalCount) {

        if (null == pageSize || pageSize < 1) {
            throw new IllegalArgumentException("参数pageSize不合法");
        }

        if (null == pageIndex || pageIndex < 1) {
            throw new IllegalArgumentException("参数pageIndex不合法");
        }

        if (null != orderBy && orderBy.trim().length() > 0 && null == isDesc) {
            throw new IllegalArgumentException("参数isDesc不合法，当orderBy指定列时，为必填参数");
        }

        if (null == needTotalCount) {
            throw new IllegalArgumentException("参数needTotalCount不能为空");
        }

        PageQuery pageQuery = new PageQuery();
        pageQuery.pageSize = pageSize;
        pageQuery.pageIndex = pageIndex;
        pageQuery.orderBy = orderBy;
        pageQuery.isDesc = isDesc;
        pageQuery.needTotalCount = needTotalCount;
        return pageQuery;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public Boolean isDesc() {
        return isDesc;
    }

    public Integer getOffset() {
        return (pageIndex - 1) * pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Boolean needTotalCount() {
        return needTotalCount;
    }

}
