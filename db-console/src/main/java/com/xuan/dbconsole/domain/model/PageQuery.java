package com.xuan.dbconsole.domain.model;

/**
 * @author xuan
 * @since 2022/12/8
 */
public class PageQuery {
    /**
     * 第几页
     */
    private int index;
    /**
     * 返回多少条
     */
    private int size;
    /**
     * 是否返回总页数
     */
    private boolean needTotalCount;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isNeedTotalCount() {
        return needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }

}
