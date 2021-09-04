package com.xuan.mix.design.command;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class RecevierImpl1 implements Receiver {

    @Override
    public void doSomething() {
        System.out.println("RecevierImpl1");
    }
}
