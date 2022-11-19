package com.xuan.mix.design.visitor;

/**
 * 被访问元素的抽象
 *
 * @author xuan
 * @since 2022/11/17
 */
public interface Element {

    /**
     * 允许谁来访问
     *
     * @param visitor Visitor
     */
    void accept(Visitor visitor);

    /**
     * 元素的业务逻辑操作
     */
    void doSomething();

}
