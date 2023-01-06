package com.xuan.mix.an.common.utils;

import java.util.Collection;

/**
 * @author xuan
 * @since 2021/12/19
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }

}
