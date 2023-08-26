package com.xuan.xutils.design.factory;

import java.lang.reflect.Constructor;

/**
 * @author xuan
 * @since 2021/8/18
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Operation operation = OperationFactory.getOperation("+");
        if (null != operation) {
            System.out.println(operation.getResult(9D, 10D));
        }

        operation = OperationFactory.getOperation("-");
        if (null != operation) {
            System.out.println(operation.getResult(9D, 10D));
        }

        Constructor constructor = TT.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    public static class TT {
        private TT() {
            System.out.println(111);

        }
    }
}
