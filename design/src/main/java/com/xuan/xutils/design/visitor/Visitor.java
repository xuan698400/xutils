package com.xuan.xutils.design.visitor;

/**
 * 访问者抽象
 *
 * @author xuan
 * @since 2022/11/17
 */
public interface Visitor {

    void visit(ElementImpl1 element1);

    void visit(ElementImpl2 element2);
}
