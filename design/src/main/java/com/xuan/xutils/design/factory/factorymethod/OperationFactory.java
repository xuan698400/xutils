package com.xuan.xutils.design.factory.factorymethod;

import com.xuan.xutils.design.factory.simple.Operation;

/**
 * 工厂接口
 *
 * @author xuan
 * @since 2023/9/30
 */
public interface OperationFactory {

    /**
     * 获取具体的操作算法
     *
     * @return 操作算法实例
     */
    Operation getOperation();
}
