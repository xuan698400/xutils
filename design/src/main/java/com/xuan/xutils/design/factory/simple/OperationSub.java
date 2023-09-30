package com.xuan.xutils.design.factory.simple;

/**
 * 减法操作
 *
 * @author xuan
 * @since 2021/8/18
 */
public class OperationSub implements Operation {

    @Override
    public double compute(double num1, double num2) {
        return num1 - num2;
    }

}
