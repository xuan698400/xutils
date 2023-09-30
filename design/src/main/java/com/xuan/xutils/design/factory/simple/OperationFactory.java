package com.xuan.xutils.design.factory.simple;

/**
 * 操作算法工厂
 *
 * @author xuan
 * @since 2021/8/18
 */
public class OperationFactory {

    /**
     * 根据操作符获取具体的操作算法
     *
     * @param operate 操作符号
     * @return 操作算法实例
     */
    public static Operation getOperation(String operate) {
        switch (operate) {
            case "+":
                return new OperationAdd();
            case "-":
                return new OperationSub();
            default:
                throw new IllegalArgumentException("操作符号不合法");
        }
    }

}
