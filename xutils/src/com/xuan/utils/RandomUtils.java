package com.xuan.utils;

/**
 * 生成随机数字、字符串的工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:10:13 $
 */
public abstract class RandomUtils {

    // 没有添加 I、O 的原因是避免和数字 1、0 混淆
    private static final String ALPHA_NUMERIC = "ABCDEFGHJKLMNPQRSTUVWXYZ123456789";

    /**
     * 产生一个固定范围（min-max 之间）的随机正整数。
     * 
     * @param min
     *            最小值，
     * @param max
     *            最大值
     * @return min-max 之间的随机整数，包括 min、max
     */
    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * 产生固定长度的随机数字串。
     * 
     * @param length
     *            长度
     * @return 随机数字串
     */
    public static String getRandomNum(int length) {
        return (Double.toString(Math.random())).substring(2, (2 + length));
    }

    /**
     * 产生固定长度的随机字母数字串，其中字母为大写方式。
     * 
     * @param length
     *            长度
     * @return 随机字母数字串
     */
    public static String getRandomStr(int length) {
        char[] randomBytes = new char[length];
        for (int i = 0; i < length; i++) {
            randomBytes[i] = ALPHA_NUMERIC.charAt(getRandomInt(0, ALPHA_NUMERIC.length() - 1));
        }
        return new String(randomBytes);
    }

    /**
     * 产生固定长度的随机字母数字串，其中字母为小写方式。
     * 
     * @param length
     *            长度
     * @return 随机字母数字串
     */
    public static String getRandomStrLowerCase(int length) {
        return getRandomStr(length).toLowerCase();
    }

}
