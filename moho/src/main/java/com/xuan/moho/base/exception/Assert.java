package com.xuan.moho.base.exception;

import java.util.Collection;
import java.util.Map;

import com.xuan.moho.base.utils.CollectionUtils;
import com.xuan.moho.base.utils.MapUtils;
import com.xuan.moho.base.utils.StringUtils;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class Assert {

    public static void isTrue(boolean expression, String code, String msg) {
        if (!expression) {
            throw new BizException(code, msg);
        }
    }

    public static void isFalse(boolean expression, String code, String msg) {
        if (expression) {
            throw new BizException(code, msg);
        }
    }

    public static void notNull(Object object, String code, String msg) {
        if (object == null) {
            throw new BizException(code, msg);
        }
    }

    public static void notEmpty(String str, String code, String msg) {
        if (StringUtils.isEmpty(str)) {
            throw new BizException(code, msg);
        }
    }

    public static void notEmpty(Collection<?> collection, String code, String errMessage) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(code, errMessage);
        }
    }

    public static void notEmpty(Map<?, ?> map, String code, String msg) {
        if (MapUtils.isEmpty(map)) {
            throw new BizException(code, msg);
        }
    }

}
