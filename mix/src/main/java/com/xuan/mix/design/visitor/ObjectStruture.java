package com.xuan.mix.design.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 结构对象
 *
 * @author xuan
 * @since 2022/11/19
 */
public class ObjectStruture {

    public static List<Element> getList() {
        List<Element> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i > 5) {
                list.add(new ElementImpl1());
            } else {
                list.add(new ElementImpl2());
            }
        }
        return list;
    }

}
