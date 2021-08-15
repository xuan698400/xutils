package com.xuan.mix.design.strategy;

/**
 * 执行策略环境（上下文）
 *
 * @author xuan
 * @since 2021/8/15
 */
public class StrategyContext {

    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doSomething() {
        strategy.doSomething();
    }

}
