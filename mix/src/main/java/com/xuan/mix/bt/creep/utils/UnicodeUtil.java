package com.xuan.mix.bt.creep.utils;

/**
 * @author xuan
 * @since 2020/11/1
 */
public class UnicodeUtil {
    private static final String UNICODE_TAG = "\\u";

    public static String toStr(String unicode) {
        if (unicode == null || "".equals(unicode)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i;
        int pos = 0;
        while ((i = unicode.indexOf(UNICODE_TAG, pos)) != -1) {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char)Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        return sb.toString();
    }

}
