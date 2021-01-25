package com.xuan.ext.framework.core.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class Joiner {

    public static String join(String separator, String... strs) {
        return join(separator, new HashSet<>(Arrays.asList(strs)));
    }

    public static String join(String separator, Set<String> strSet) {
        if (null == strSet) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : strSet) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(str);
            i++;
        }
        return sb.toString();
    }

}
