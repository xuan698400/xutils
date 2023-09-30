package com.xuan.xutils.design.factory.simple;

/**
 * @author xuan
 * @since 2021/8/18
 */
public class Main {

    public static void main(String[] args) {

        //测试获取相加操作
        Operation operation = OperationFactory.getOperation("+");
        System.out.println("俩数相加计算结果：" + operation.compute(9D, 10D));

        //测试获取相减操作
        operation = OperationFactory.getOperation("-");
        System.out.println("俩数相减计算结果：" + operation.compute(9D, 10D));
    }

}
