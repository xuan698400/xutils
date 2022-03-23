package com.xuan.mix.bt.etlflow.json2model;

import java.util.List;

/**
 * @author xuan
 * @since 2022/3/17
 */
public class EtlFlowJsonObj {

    private String id;

    private String name;

    private List<String> flowNodes;

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

    public List<String> getFlowNodes() {
        return flowNodes;
    }

    public void setFlowNodes(List<String> flowNodes) {
        this.flowNodes = flowNodes;
    }
    
}
