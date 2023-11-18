package com.xuan.crudboy.utils;

import java.util.Collection;

/**
 * 集合常用工具类
 *
 * @author xuan
 * @since 2021/12/19
 */
public class CbCollectionUtils {

    /**
     * 判断集合是否为空
     * 集合为NULL或者集合没有元素都算是空
     *
     * @param collection 集合
     * @return true/false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判断集合是否为非空
     * 集合不为NULL且集合有元素才会判定非空
     *
     * @param collection 集合
     * @return true/false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
