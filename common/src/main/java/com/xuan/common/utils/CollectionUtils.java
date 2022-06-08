package com.xuan.common.utils;

import java.util.Collection;

/**
 * 集合常用工具类
 *
 * @author xuan
 * @since 2021/12/19
 */
public class CollectionUtils {

    /**
     * 集合是否为空，null或者空集合都返回true，但如果集合里面只有null元素，返回false的
     *
     * @param collection 集合
     * @return true/false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 集合是否不为空。!isEmpty
     *
     * @param coll 集合
     * @return true/false
     */
    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }

}
