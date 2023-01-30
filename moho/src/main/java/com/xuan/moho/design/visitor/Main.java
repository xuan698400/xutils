package com.xuan.moho.design.visitor;

import java.util.List;

/**
 * 访问者模式定义：封装一些作用于某种数据结构中各种元素的操作，他可以在不改变数据结构的前提下定义作用于这个元素的新的操作
 *
 * @author xuan
 * @since 2022/11/17
 */
public class Main {

    public static void main(String[] args) {
        List<Element> list = ObjectStruture.getList();
        for (Element e : list) {
            //这里要替换访问者很容易
            e.accept(new VisitorImpl1());
        }
    }

}
