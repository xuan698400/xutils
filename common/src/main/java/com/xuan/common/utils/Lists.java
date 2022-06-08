package com.xuan.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class Lists {

    public static <E> List<E> newList() {
        return new ArrayList<>();
    }

    public static <E> List<E> newList(E... elements) {
        List<E> list = new ArrayList<>();
        Collections.addAll(list, elements);
        return list;
    }

    public static <E> List<E> newList(Set<E> set) {
        return new ArrayList<>(set);
    }

}
