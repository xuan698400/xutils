package com.xuan.xutils.design.strategy;

/**
 * 高层模块，用来执行不同的策略
 *
 * @author xuan
 * @since 2021/8/15
 */
public class Client {

    public static void main(String[] args) {
        //相加
        Context context = new Context(new StrategyAdd());
        System.out.println("20 + 10 = " + context.calculate(20, 10));
        //相减
        context = new Context(new StrategySub());
        System.out.println("20 - 10 = " + context.calculate(20, 10));
        //相乘
        context = new Context(new StrategyMul());
        System.out.println("20 * 10 = " + context.calculate(20, 10));
        //相除
        context = new Context(new StrategyDiv());
        System.out.println("20 / 10 = " + context.calculate(20, 10));
    }
}
