package com.xuan.xutils.sql.common;

/**
 * @author xuan
 * @since 2023/12/12
 */
public class StringUtils {

    /**
     * 字符串判空
     * 字符串为NULL或者长度为0算空，注意" "也会被判定非空，该判断和Apache的common包的isEmpty逻辑一致
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判非空，!isEmpty()
     *
     * @param cs 字符串
     * @return true/false
     */
    public static boolean isNotEmpty(String cs) {
        return !isEmpty(cs);
    }
}
