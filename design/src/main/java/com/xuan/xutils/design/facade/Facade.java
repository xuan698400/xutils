package com.xuan.xutils.design.facade;

/**
 * 门面
 *
 * @author xuan
 * @since 2023/9/30
 */
public class Facade {

    private ClassA classA = new ClassA();
    private ClassB classB = new ClassB();
    private ClassC classC = new ClassC();

    public void doA() {
        classA.doSomethingA();
    }

    public void doB() {
        classB.doSomethingB();
    }

    public void doC() {
        classC.doSomethingC();
    }
}
