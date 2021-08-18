package com.xuan.mix.design.factory;

/**
 * @author xuan
 * @since 2021/8/18
 */
public class Main {

    public static void main(String[] args) {
        Operation operation = OperationFactory.getOperation("+");
        if (null != operation) {
            System.out.println(operation.getResult(9D, 10D));
        }

        operation = OperationFactory.getOperation("-");
        if (null != operation) {
            System.out.println(operation.getResult(9D, 10D));
        }
    }
}
