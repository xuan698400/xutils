package com.xuan.moho.design.visitor;

/**
 * 具体元素2
 *
 * @author xuan
 * @since 2022/11/17
 */
public class ElementImpl2 implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void doSomething() {
        System.out.println("doSomething ElementImpl2");
    }

}
