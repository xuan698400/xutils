package com.xuan.dao.common;

import java.lang.reflect.Field;

/**
 * @author xuan
 * @since 2022/1/25
 */
public class ReflectUtils {

    public static String getFieldName(Field field) {
        return camel4underline(field.getName());
    }

    public static String camel4underline(String str) {
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

}
