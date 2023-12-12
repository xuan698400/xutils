package com.xuan.xutils.sql.orm.model.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询
 *
 * @author xuan
 * @since 2022/6/1
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否需要返回总页数
     */
    private boolean needTotalCount = false;

    /**
     * 分页数量
     */
    private int pageSize = 10;

    /**
     * 分页页数，从1开始表示第一页
     */
    private int pageIndex = 1;

    /**
     * 排序
     */
    private List<PageOrderBy> orderByList;

    /**
     * 分页offset，一般查询数据库时会用到
     *
     * @return offset
     */
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
