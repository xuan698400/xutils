package com.xuan.xutils.design.factory;

/**
 * @author xuan
 * @since 2021/8/18
 */
public class OperationFactory {

    public static Operation getOperation(String operate) {
        switch (operate) {
            case "+":
                return new OperationAdd();
            case "-":
                return new OperationSub();
            default:
                return null;
        }
    }
}
