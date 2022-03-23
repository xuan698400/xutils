package com.xuan.mix.bt.etlflow.model.node;

/**
 * @author xuan
 * @since 2022/3/17
 */
public class TableNode extends AbstractFlowNode {

    private String tableName;

    public static TableNode of(String id, String name, String tableName) {
        TableNode tableNode = new TableNode();
        tableNode.setId(id);
        tableNode.setName(name);
        tableNode.setTableName(tableName);
        return tableNode;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
