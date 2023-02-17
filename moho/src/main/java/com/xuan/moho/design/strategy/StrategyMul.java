package com.xuan.moho.design.strategy;

/**
 * 相乘策略
 *
 * @author xuan
 * @since 2021/8/15
 */
public class StrategyMul implements Strategy {

    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}
