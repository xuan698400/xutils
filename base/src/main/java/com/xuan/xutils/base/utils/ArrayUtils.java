package com.xuan.xutils.base.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 数组工具类
 *
 * @author xuan
 * @since 2021/12/19
 */
public class ArrayUtils {

    /**
     * 判空，null或者空数组都返回true
     *
     * @param array 数组
     * @return true/false
     */
    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    /**
     * 获取数组长度，如果是null返回0
     *
     * @param array 数据
     * @return 长度
     */
    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * 显示字符串数组的内容，用指,符连接
     *
     * @param args 字符串数组
     * @return 字符串数组的内容
     */
    public static String toString(String[] args) {
        return toString(args, ",");
    }

    /**
     * 显示字符串数组的内容，用指定分隔符连接
     *
     * @param args      字符串数组
     * @param separator 分隔符
     * @return 字符串数组的内容
     */
    public static String toString(String[] args, String separator) {
        if (null == args || args.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(args[i]);
        }
        return sb.toString();
    }

    /**
     * 取得字符串数组的第一个元素
     *
     * @param array 字符串数组
     * @return 字符串数组的第一个元素
     */
    public static String getFirst(String[] array) {
        if (null == array || array.length == 0) {
            return null;
        }
        return array[0];
    }

    /**
     * 取得数组的第一个元素
     *
     * @param array 数组
     * @return 数组的第一个元素
     */
    public static Object getFirst(Object[] array) {
        if (null == array || array.length == 0) {
            return null;
        }
        return array[0];
    }

    /**
     * 把List转换成字符串数组
     *
     * @param list 字符串List
     * @return 字符串数组
     */
    public static String[] toArrayString(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new String[0];
        }
        return list.toArray(new String[0]);
    }

    public static Object[] toArrayObject(List<Object> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new String[0];
        }
        return list.toArray(new Object[0]);
    }

    /**
     * 把Set转换成字符串数组
     *
     * @param set 字符串Set
     * @return 字符串数组
     */
    public static String[] toArray(Set<String> set) {
        return set.toArray(new String[0]);
    }

    /**
     * 判断字符串数组是否包含指定的字符串
     *
     * @param array 字符串数组
     * @param str   指定的字符串
     * @return 包含true，否则false
     */
    public static boolean contains(String[] array, String str) {
        if (null == array || array.length == 0) {
            return false;
        }

        for (String s : array) {
            if (Objects.equals(s, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串数组是否有不为Empty的值
     *
     * @param args 字符串数组
     * @return 有true，否则false
     */
    public static boolean hasValue(String[] args) {
        if (null == args || args.length == 0) {
            return false;
        }
        for (String str : args) {
            if (null != str && str.trim().length() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 联合两个数组
     *
     * @param first 第一个数组
     * @param last  另一个数组
     * @return 内容合并后的数组
     */
    public static Object[] combine(Object[] first, Object[] last) {
        if (0 == first.length && 0 == last.length) {
            return new Object[0];
        }
        Object[] result = new Object[first.length + last.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(last, 0, result, first.length, last.length);
        return result;
    }

    /**
     * 把数组转换成列表，如果数组为 null，则会返回一个空列表。
     *
     * @param array 数组
     * @return 列表对象
     */
    public static List<Object> toList(Object[] array) {
        List<Object> list = new ArrayList<>();
        if (null == array) {
            return list;
        }
        Collections.addAll(list, array);
        return list;
    }

    /**
     * 清除字符串数组中的null
     *
     * @param array 字符串数组
     * @return 清除null后的字符串数组
     */
    public static String[] clearNull(String[] array) {
        List<String> list = new ArrayList<>();
        for (String str : array) {
            if (null != str) {
                list.add(str);
            }
        }
        return toArrayString(list);
    }

}
