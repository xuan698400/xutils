package com.xuan.crudboy.sql.builder.core;

/**
 * @author xuan
 * @since 2023/11/6
 */
public class CbSqlHelper {

    /**
     * 驼峰格式转下划线格式
     *
     * @param str 驼峰格式
     * @return 下划线格式
     */
    public static String camelToUnderline(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(c));
        }
        return builder.toString();
    }

    /**
     * 下划线格式转驼峰格式
     *
     * @param str 下划线格式
     * @return 驼峰格式
     */
    public static String underlineToCamel(String str) {
        if (null == str || str.trim().length() == 0) {
            return "";
        }

        if (!str.contains("_")) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        String[] words = str.split("_");
        boolean first = true;
        for (String s : words) {
            if (null == s || s.trim().length() == 0) {
                continue;
            }

            if (first) {
                sb.append(s);
            } else {
                //非首个词，首字母大写
                sb.append(s.substring(0, 1).toUpperCase());
                sb.append(s.substring(1));
            }
            first = false;
        }

        return sb.toString();
    }
    
}
