package com.xuan.xutils.sql.common;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 *
 * @author xuan
 * @since 2021/7/12
 */
public class Assert {

    /**
     * 字符串不能为空，如果为空就抛出异常
     *
     * @param str 被检查字符串
     * @param msg 错误提示
     */
    public static void notEmpty(String str, String msg) {
        if (null == str || str.trim().length() == 0) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 对象不能为null，如果为null就抛出异常
     *
     * @param obj 被检查对象
     * @param msg 错误提示
     */
    public static void notNull(Object obj, String msg) {
        if (null == obj) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 集合不能为空，如果为空就抛出异常
     *
     * @param collection 被检查集合
     * @param msg        错误提示
     */
    public static void notEmpty(Collection<?> collection, String msg) {
        if (null == collection || collection.isEmpty()) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * MAP不能为空，如果为空就抛出异常
     *
     * @param map 被检查MAP
     * @param msg 错误提示
     */
    public static void notEmpty(Map<?, ?> map, String msg) {
        if (null == map || map.isEmpty()) {
            throw new RuntimeException(msg);
        }
    }

}
