package com.xuan.xutils.design.factory.factorymethod;

import com.xuan.xutils.design.factory.simple.Operation;

/**
 * @author xuan
 * @since 2023/9/30
 */
public class Main {

    public static void main(String[] args) {

        //测试获取相加操作
        OperationFactory operationFactory = new AddOperationFactory();
        Operation operation = operationFactory.getOperation();
        System.out.println("俩数相加计算结果：" + operation.compute(9D, 10D));

        //测试获取相减操作
        operationFactory = new SubOperationFactory();
        operation = operationFactory.getOperation();
        System.out.println("俩数相减计算结果：" + operation.compute(9D, 10D));
    }
}
