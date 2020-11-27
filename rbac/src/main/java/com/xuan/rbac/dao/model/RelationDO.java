package com.xuan.rbac.dao.model;

/**
 * @author xuan
 * @since 2020/11/27
 */
public class RelationDO extends BaseDO {

    private Long fromId;

    private Long toId;

    @Override
    public String toString() {
        return super.toString() + ", RelationDO{" +
            "fromId=" + fromId +
            ", toId=" + toId +
            '}';
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

}
