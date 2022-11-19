package com.xuan.mix.design.visitor;

/**
 * 具体元素1
 *
 * @author xuan
 * @since 2022/11/17
 */
public class ElementImpl1 implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void doSomething() {
        System.out.println("doSomething ElementImpl1");
    }

}
