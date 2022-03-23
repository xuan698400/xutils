package com.xuan.mix.bt.etlflow.model.node;

/**
 * @author xuan
 * @since 2022/3/17
 */
public class SelectNode extends AbstractFlowNode {

    private String column;

    private String where;

    private String groupBy;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
