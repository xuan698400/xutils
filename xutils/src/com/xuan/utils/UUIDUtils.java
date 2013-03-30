package com.xuan.utils;

/**
 * UUID 工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:55:11 $
 */
public abstract class UUIDUtils {

    private static UUIDGenerator uuid = new UUIDGenerator();

    /**
     * 产生新的UUID
     * 
     * @return 32位长的UUID字符串
     */
    public static String newId() {
        return uuid.generateHex();
    }

}
