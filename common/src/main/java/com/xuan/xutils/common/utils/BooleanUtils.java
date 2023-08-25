package com.xuan.xutils.common.utils;

/**
 * Boolean工具类
 *
 * @author xuan
 * @since 2023/1/6
 */
public class BooleanUtils {

    /**
     * 判断b是否true，会进行判null处理
     *
     * @param b b
     * @return true/false
     */
    public static boolean isTure(Boolean b) {
        return null != b && b;
    }

}
