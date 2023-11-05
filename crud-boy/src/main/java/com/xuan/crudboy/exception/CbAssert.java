package com.xuan.crudboy.exception;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 *
 * @author xuan
 * @since 2021/7/12
 */
public class CbAssert {

    /**
     * 判断条件是否为真，如果不为真抛出异常
     *
     * @param expression 条件
     * @param code       错误码
     * @param msg        错误提示
     */
    public static void isTrue(boolean expression, CbExceptionCode code, String msg) {
        if (!expression) {
            throw CbExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 判断条件是否为否，如果不为否抛出异常
     *
     * @param expression 条件
     * @param code       错误码
     * @param msg        错误提示
     */
    public static void isFalse(boolean expression, CbExceptionCode code, String msg) {
        if (expression) {
            throw CbExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 对象不能为null，如果为null就抛出异常
     *
     * @param obj  被检查对象
     * @param code 错误码
     * @param msg  错误提示
     */
    public static void notNull(Object obj, CbExceptionCode code, String msg) {
        if (null == obj) {
            throw CbExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 字符串不能为空，如果为空就抛出异常
     *
     * @param str  被检查字符串
     * @param code 错误码
     * @param msg  错误提示
     */
    public static void notEmpty(String str, CbExceptionCode code, String msg) {
        if (null == str || str.trim().length() == 0) {
            throw CbExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 集合不能为空，如果为空就抛出异常
     *
     * @param collection 被检查集合
     * @param code       错误码
     * @param msg        错误提示
     */
    public static void notEmpty(Collection<?> collection, CbExceptionCode code, String msg) {
        if (null == collection || collection.isEmpty()) {
            throw CbExceptionFactory.exception(code, msg);
        }
    }

    /**
     * MAP不能为空，如果为空就抛出异常
     *
     * @param map  被检查MAP
     * @param code 错误码
     * @param msg  错误提示
     */
    public static void notEmpty(Map<?, ?> map, CbExceptionCode code, String msg) {
        if (null == map || map.isEmpty()) {
            throw CbExceptionFactory.exception(code, msg);
        }
    }

}
