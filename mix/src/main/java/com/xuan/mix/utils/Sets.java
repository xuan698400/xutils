package com.xuan.mix.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuan
 * @since 2022/1/1
 */
public class Sets {

    public static <E> Set<E> newSet() {
        return new HashSet<>();
    }

    public static <E> Set<E> newSet(Set<E> elements) {
        return new HashSet<>(elements);
    }

}
