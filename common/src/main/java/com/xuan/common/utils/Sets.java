package com.xuan.common.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class Sets {

    public static <E> Set<E> newSet() {
        return new HashSet<>();
    }

    public static <E> Set<E> newSet(E... elements) {
        Set<E> set = new HashSet<>();
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> Set<E> newSet(List<E> list) {
        if (null == list) {
            return new HashSet<>();
        }
        return new HashSet<>(list);
    }

}
