package com.xuan.mix.bt.etlflow.model;

import java.util.List;

import com.xuan.mix.bt.etlflow.model.node.IFlowNode;

/**
 * @author xuan
 * @since 2022/3/17
 */
public class EtlFlow {

    private String id;

    private String name;

    private List<IFlowNode> flowNodeList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IFlowNode> getFlowNodeList() {
        return flowNodeList;
    }

    public void setFlowNodeList(List<IFlowNode> flowNodeList) {
        this.flowNodeList = flowNodeList;
    }
}
