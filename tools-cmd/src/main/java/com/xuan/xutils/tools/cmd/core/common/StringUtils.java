package com.xuan.xutils.tools.cmd.core.common;

/**
 * @author xuan
 * @since 2023/8/24
 */
public class StringUtils {

    /**
     * 获取字符串长度，英文算一个，中文算2个
     *
     * @param str 字符串
     * @return 字符串长度
     */
    public static int getStringLength(String str) {
        String[] strs = str.split("\n");
        String maxStr = "";
        for (String s : strs) {
            if ("".equals(maxStr)) {
                maxStr = s;
                continue;
            }

            if (s.length() > maxStr.length()) {
                maxStr = s;
            }
        }

        int length = 0;
        for (int i = 0, n = maxStr.length(); i < n; i++) {
            int c = (int)maxStr.charAt(i);
            if (c < 40869 && c >= 19968) {
                length += 2;
            } else {
                length++;
            }
        }
        return Math.min(length, 40);
    }

    /**
     * 字符串判空
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

}
