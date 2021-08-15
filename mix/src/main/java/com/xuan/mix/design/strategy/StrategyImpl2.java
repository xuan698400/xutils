package com.xuan.mix.design.strategy;

/**
 * 策略实现1
 *
 * @author xuan
 * @since 2021/8/15
 */
public class StrategyImpl2 implements Strategy {

    @Override
    public void doSomething() {
        System.out.println("我是策略实现2");
    }
}
