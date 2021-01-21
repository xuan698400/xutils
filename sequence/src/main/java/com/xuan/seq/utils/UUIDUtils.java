package com.xuan.seq.utils;

import java.net.InetAddress;

/**
 * UUID 生成工具类
 *
 * @author xuan
 * @date 2018/5/30
 */
public class UUIDUtils {

    private static UUIDUtils uuid = new UUIDUtils();

    private static final int IP;

    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private static short counter = (short)0;
    private static final int JVM = (int)(System.currentTimeMillis() >>> 8);

    private UUIDUtils() {
    }

    /**
     * 生成16进制表达的字符串 UUID。
     *
     * @return 32 字节长度的 UUID 字符串
     */
    private String generateHex() {
        return (format(getIP()) + format(getJVM()) + format(getHighTime()) + format(getLowTime()) + format(
            getCount())).toUpperCase();
    }

    private String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuilder buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    /**
     * Unique across JVMs on this machine (unless they load this class in the same quater second - very unlikely)
     */
    private int getJVM() {
        return JVM;
    }

    /**
     * Unique in a millisecond for this JVM instance (unless there are > Short.MAX_VALUE instances created in a
     * millisecond)
     */
    private short getCount() {
        synchronized (UUIDUtils.class) {
            if (counter < 0) {
                counter = 0;
            }
            return counter++;
        }
    }

    /**
     * Unique in a local network
     */
    private int getIP() {
        return IP;
    }

    /**
     * Unique down to millisecond
     */
    private short getHighTime() {
        return (short)(System.currentTimeMillis() >>> 32);
    }

    private int getLowTime() {
        return (int)System.currentTimeMillis();
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + bytes[i];
        }
        return result;
    }

    /**
     * 产生新的UUID
     *
     * @return 32位长的UUID字符串
     */
    public static String uuid() {
        return uuid.generateHex();
    }

}
