package com.xuan.common.utils;

import java.util.List;

/**
 * 字符串常用工具类
 *
 * @author xuan
 * @since 2021/12/19
 */
public class StringUtils {

    /**
     * 空格常量
     */
    public static final String SPACE = " ";

    /**
     * 空字符串常量
     */
    public static final String EMPTY = "";

    /**
     * 判空，如果是null或者长度为0都返回true
     *
     * @param cs 字符串
     * @return true/false
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判非空，!isEmpty
     *
     * @param cs 字符串
     * @return true/false
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判blank，如果是null或者长度为0都返回true，并且，如果串都是有空格组成也返回true
     *
     * @param cs 字符串
     * @return true/false
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 判非blank，!isBlank
     *
     * @param cs 字符串
     * @return true/false
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 字符串数组中，有任意blank串的数据，就返回true。本身是null或者空元素也返回true
     *
     * @param css 字符串数组
     * @return true/false
     */
    public static boolean isAnyBlank(final CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return true;
        }
        for (final CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * !isAnyBlank
     *
     * @param css 字符串数组
     * @return true/false
     */
    public static boolean isNoneBlank(final CharSequence... css) {
        return !isAnyBlank(css);
    }

    /**
     * 字符串trim，防空指针，如果是null就返回null
     *
     * @param str 字符串
     * @return trim后的串
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 字符串trim，如果是空串，会返回null
     *
     * @param str 字符串
     * @return trim后的串
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * 字符串trim，如果是null，会返回空串
     *
     * @param str 字符串
     * @return trim后的串
     */
    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * 对象toString后trim，如果是null，会返回空串
     *
     * @param obj 对象
     * @return 对象toString后的串
     */
    public static String trimToEmpty(final Object obj) {
        return obj == null ? EMPTY : obj.toString().trim();
    }

    /**
     * 字符串进行截断，从最左侧开始
     *
     * @param str      字符串
     * @param maxWidth 最大长度
     * @return 截断后的字符串
     */
    public static String truncate(final String str, int maxWidth) {
        return truncate(str, 0, maxWidth);
    }

    /**
     * 字符串进行截断
     *
     * @param str      字符串
     * @param offset   截断开始的地方
     * @param maxWidth 最大长度
     * @return 截断后的字符串
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
            return EMPTY;
        }
        if (str.length() > maxWidth) {
            int ix = offset + maxWidth > str.length() ? str.length() : offset + maxWidth;
            return str.substring(offset, ix);
        }
        return str.substring(offset);
    }

    /**
     * 判断两个字符串是否相等，都是null返回true，一个null一个非null返回false
     *
     * @param cs1 字符串
     * @param cs2 字符串
     * @return true/false
     */
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

    /**
     * 返回字符串，如果为空，返回指定的默认值
     *
     * @param str        返回字符串
     * @param defaultStr 默认值
     * @return 字符串
     */
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
        String[] strs = str.split(sp);
        List<String> list = Lists.newList();
        for (String s : strs) {
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
