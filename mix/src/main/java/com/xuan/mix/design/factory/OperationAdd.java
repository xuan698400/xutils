package com.xuan.mix.design.factory;

/**
 * @author xuan
 * @since 2021/8/18
 */
public class OperationAdd implements Operation {

    @Override
    public double getResult(double num1, double num2) {
        return num1 + num2;
    }

}
