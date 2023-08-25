package com.xuan.xutils.common.exception;

import java.util.Collection;
import java.util.Map;

import com.xuan.xutils.common.utils.CollectionUtils;
import com.xuan.xutils.common.utils.MapUtils;
import com.xuan.xutils.common.utils.StringUtils;

/**
 * 断言工具类
 *
 * @author xuan
 * @since 2021/7/12
 */
public class Assert {

    /**
     * 判断条件是否为真，如果不为真抛出异常
     *
     * @param expression 条件
     * @param code       错误码
     * @param msg        错误提示
     */
    public static void isTrue(boolean expression, BizExceptionCode code, String msg) {
        if (!expression) {
            throw ExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 判断条件是否为否，如果不为否抛出异常
     *
     * @param expression 条件
     * @param code       错误码
     * @param msg        错误提示
     */
    public static void isFalse(boolean expression, BizExceptionCode code, String msg) {
        if (expression) {
            throw ExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 对象不能为null，如果为null就抛出异常
     *
     * @param obj  被检查对象
     * @param code 错误码
     * @param msg  错误提示
     */
    public static void notNull(Object obj, BizExceptionCode code, String msg) {
        if (null == obj) {
            throw ExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 字符串不能为空，如果为空就抛出异常
     *
     * @param str  被检查字符串
     * @param code 错误码
     * @param msg  错误提示
     */
    public static void notEmpty(String str, BizExceptionCode code, String msg) {
        if (StringUtils.isEmpty(str)) {
            throw ExceptionFactory.exception(code, msg);
        }
    }

    /**
     * 集合不能为空，如果为空就抛出异常
     *
     * @param collection 被检查集合
     * @param code       错误码
     * @param msg        错误提示
     */
    public static void notEmpty(Collection<?> collection, BizExceptionCode code, String msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw ExceptionFactory.exception(code, msg);
        }
    }

    /**
     * MAP不能为空，如果为空就抛出异常
     *
     * @param map  被检查MAP
     * @param code 错误码
     * @param msg  错误提示
     */
    public static void notEmpty(Map<?, ?> map, BizExceptionCode code, String msg) {
        if (MapUtils.isEmpty(map)) {
            throw ExceptionFactory.exception(code, msg);
        }
    }

}
