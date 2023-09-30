package com.xuan.xutils.design.factory.factorymethod;

import com.xuan.xutils.design.factory.simple.Operation;
import com.xuan.xutils.design.factory.simple.OperationAdd;

/**
 * 相加操作工厂
 *
 * @author xuan
 * @since 2023/9/30
 */
public class AddOperationFactory implements OperationFactory {

    @Override
    public Operation getOperation() {
        return new OperationAdd();
    }
}
