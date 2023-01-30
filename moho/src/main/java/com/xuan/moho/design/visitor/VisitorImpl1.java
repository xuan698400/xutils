package com.xuan.moho.design.visitor;

/**
 * 具体访问者实现
 *
 * @author xuan
 * @since 2022/11/17
 */
public class VisitorImpl1 implements Visitor {

    @Override
    public void visit(ElementImpl1 element1) {
        element1.doSomething();
    }

    @Override
    public void visit(ElementImpl2 element2) {
        element2.doSomething();
    }

}
