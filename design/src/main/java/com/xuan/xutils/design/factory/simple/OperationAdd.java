package com.xuan.xutils.design.factory.simple;

/**
 * 加法操作
 *
 * @author xuan
 * @since 2021/8/18
 */
public class OperationAdd implements Operation {

    @Override
    public double compute(double num1, double num2) {
        return num1 + num2;
    }

}
