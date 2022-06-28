package com.xuan.common.utils;

import java.util.List;

/**
 * 字符串常用工具类
 *
 * @author xuan
 * @since 2021/12/19
 */
public class StringUtils {
    
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    public static String truncate(final String str, int maxWidth) {
        return truncate(str, 0, maxWidth);
    }

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
        if (str.length() > maxWidth) {
            int ix = offset + maxWidth > str.length() ? str.length() : offset + maxWidth;
            return str.substring(offset, ix);
        }
        return str.substring(offset);
    }

    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        return cs1.equals(cs2);
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return StringUtils.isEmpty(str) ? defaultStr : str;
    }

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

    public static String join(List<String> strList) {
        return join(strList, ",");
    }

    public static List<String> split(String str, String sp) {
        if (StringUtils.isEmpty(str)) {
            return Lists.newList();
        }
        String[] items = str.split(sp);
        List<String> list = Lists.newList();
        for (String s : items) {
            if (StringUtils.isNotEmpty(s)) {
                list.add(s);
            }
        }
        return list;
    }

    public static List<String> split(String str) {
        return StringUtils.split(str, ",");
    }

}
