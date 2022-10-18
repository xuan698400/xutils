package com.xuan.mix.biz.account.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xuan
 * @since 2022/10/19
 */
public class SecurityUtils {

    public static String encodeByMD5(String str) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(getBytesUTF8(str));
            return toHexString(e.digest());
        } catch (NoSuchAlgorithmException var2) {
            throw new RuntimeException("Could not encodeByMD5", var2);
        }
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; ++i) {
            hexString.append(enoughZero(Integer.toHexString(bytes[i] & 255), 2));
        }

        return hexString.toString();
    }

    private static String enoughZero(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }

        return str;
    }

    private static byte[] getBytesUTF8(String str) {
        return getBytes(str, "utf8");
    }

    private static byte[] getBytes(String str, String charsetName) {
        try {
            return str.getBytes(charsetName);
        } catch (UnsupportedEncodingException var3) {
            return null;
        }
    }

}
