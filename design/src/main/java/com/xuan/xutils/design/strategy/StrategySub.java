package com.xuan.xutils.design.strategy;

/**
 * 相减策略
 *
 * @author xuan
 * @since 2021/8/15
 */
public class StrategySub implements Strategy {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}
