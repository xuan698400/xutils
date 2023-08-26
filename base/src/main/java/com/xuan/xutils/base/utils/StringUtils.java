package com.xuan.xutils.base.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串常用工具类
 *
 * @author xuan
 * @since 2021/12/19
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

    /**
     * 字符串trim
     *
     * @param str 字符串
     * @return trim后的串
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 字符串截取，从第一个字符串开始
     *
     * @param str      原始字符串
     * @param maxWidth 截取长度
     * @return 截图后字符串
     */
    public static String truncate(final String str, int maxWidth) {
        return truncate(str, 0, maxWidth);
    }

    /**
     * 字符串截取
     *
     * @param str      原始字符串
     * @param offset   截图开始位置，从0开始
     * @param maxWidth 截取长度
     * @return 截图后字符串
     */
    public static String truncate(final String str, int offset, int maxWidth) {
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        if (maxWidth < 0) {
            throw new IllegalArgumentException("maxWith cannot be negative");
        }
        if (str == null) {
            return null;
        }
        if (offset > str.length()) {
            return "";
        }
        if (maxWidth > str.length()) {
            return str.substring(offset);
        }

        int ix = offset + maxWidth > str.length() ? str.length() : offset + maxWidth;
        return str.substring(offset, ix);
    }

    /**
     * 字符串相等比较
     *
     * @param str1 str1
     * @param str2 str2
     * @return true/false
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 返回字符串，如果为空使用默认值代替
     *
     * @param str        原始字符串
     * @param defaultStr 代替值
     * @return 字符串
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return StringUtils.isEmpty(str) ? defaultStr : str;
    }

    /**
     * 用特定分隔符聚合字符串
     *
     * @param strList 字符串列表
     * @param sp      分隔符
     * @return 聚合后的字符串
     */
    public static String join(List<String> strList, String sp) {
        if (CollectionUtils.isEmpty(strList)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strList) {
            if (sb.length() > 0) {
                sb.append(sp);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 用,聚合字符串
     *
     * @param strList 字符串列表
     * @return 聚合后的字符串
     */
    public static String join(List<String> strList) {
        return join(strList, ",");
    }

    /**
     * 使用特定分隔符，分割字符串
     *
     * @param str 字符串
     * @param sp  分割符
     * @return 分割后字符串列表（会去除空字符串）
     */
    public static List<String> split(String str, String sp) {
        if (StringUtils.isEmpty(str)) {
            return new ArrayList<>();
        }
        String[] items = str.split(sp);
        List<String> list = new ArrayList<>();
        for (String s : items) {
            if (StringUtils.isNotEmpty(s)) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * 使用,分割字符串
     *
     * @param str 字符串
     * @return 分割后字符串列表（会去除空字符串）
     */
    public static List<String> split(String str) {
        return StringUtils.split(str, ",");
    }

}
