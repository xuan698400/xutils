package com.xuan.mix.bt.etlflow.model.node;

/**
 * @author xuan
 * @since 2022/3/17
 */
public abstract class AbstractFlowNode implements IFlowNode {

    private String id;

    private String name;

    private String to;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
