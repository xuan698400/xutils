package com.xuan.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 数组工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:56:54 $
 */
public abstract class ArrayUtils {

    /**
     * 显示字符串数组的内容，用,分隔
     * 
     * @param args
     *            字符串数组
     * @return 字符串数组的内容
     */
    public static String toString(String[] args) {
        return toString(args, ",");
    }

    /**
     * 显示字符串数组的内容
     * 
     * @param args
     *            字符串数组
     * @param separator
     *            分隔符
     * @return 字符串数组的内容
     */
    public static String toString(String[] args, String separator) {
        if (Validators.isEmpty(args)) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                buffer.append(separator);
            }
            buffer.append(args[i]);
        }
        return buffer.toString();
    }

    /**
     * 取得字符串数组的第一个元素
     * 
     * @param stringArray
     *            字符串数组
     * @return 字符串数组的第一个元素
     */
    public static String getFirst(String[] stringArray) {
        if (stringArray == null || stringArray.length == 0) {
            return null;
        }
        return stringArray[0];
    }

    /**
     * 取得数组的第一个元素
     * 
     * @param array
     *            数组
     * @return 数组的第一个元素
     */
    public static Object getFirst(Object[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    /**
     * 把List转换成字符串数组
     * 
     * @param list
     *            字符串List
     * @return 字符串数组
     */
    public static String[] toArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    /**
     * 把Set转换成字符串数组
     * 
     * @param set
     *            字符串Set
     * @return 字符串数组
     */
    public static String[] toArray(Set<String> set) {
        return set.toArray(new String[set.size()]);
    }

    /**
     * 判断字符串数组是否包含指定的字符串
     * 
     * @param array
     *            字符串数组
     * @param str
     *            指定的字符串
     * @return 包含true，否则false
     */
    public static boolean contains(String[] array, String str) {

        if (Validators.isEmpty(array)) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串数组是否有不为Empty的值
     * 
     * @param args
     *            字符串数组
     * @return 有true，否则false
     */
    public static boolean hasValue(String[] args) {
        if (Validators.isEmpty(args)) {
            return false;
        }
        for (int i = 0, length = args.length; i < length; i++) {
            if (!Validators.isEmpty(args[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 联合两个数组
     * 
     * @param first
     *            第一个数组
     * @param last
     *            另一个数组
     * @return 内容合并后的数组
     */
    public static Object[] combine(Object[] first, Object[] last) {
        if (first.length == 0 && last.length == 0) {
            return null;
        }
        Object[] result = new Object[first.length + last.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(last, 0, result, first.length, last.length);
        return result;
    }

    /**
     * 把数组转换成 列表，如果数组为 null，则会返回一个空列表。
     * 
     * @param array
     *            数组
     * @return 列表对象
     */
    public static List<Object> toList(Object[] array) {
        ArrayList<Object> list = new ArrayList<Object>();
        if (array == null) {
            return list;
        }

        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * 清除字符串数组中的null
     * 
     * @param array
     *            字符串数组
     * @return 清除null后的字符串数组
     */
    public static String[] clearNull(String[] array) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                list.add(array[i]);
            }
        }
        return toArray(list);
    }

}
