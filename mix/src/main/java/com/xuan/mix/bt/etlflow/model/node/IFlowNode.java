package com.xuan.mix.bt.etlflow.model.node;

/**
 * @author xuan
 * @since 2022/3/17
 */
public interface IFlowNode {

    String getId();

    String getName();

    String getTo();
}
