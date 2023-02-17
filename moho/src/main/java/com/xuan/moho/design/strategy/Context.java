package com.xuan.moho.design.strategy;

/**
 * 持有策略上下文
 *
 * @author xuan
 * @since 2021/8/15
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int num1, int num2) {
        return strategy.calculate(num1, num2);
    }
}
