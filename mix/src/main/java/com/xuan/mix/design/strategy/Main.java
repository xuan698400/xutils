package com.xuan.mix.design.strategy;

/**
 * 高层模块，用来执行不同的策略
 *
 * @author xuan
 * @since 2021/8/15
 */
public class Main {

    public static void main(String[] args) {
        StrategyContext context = new StrategyContext(new StrategyImpl1());
        context.doSomething();

        context = new StrategyContext(new StrategyImpl2());
        context.doSomething();
    }
}
