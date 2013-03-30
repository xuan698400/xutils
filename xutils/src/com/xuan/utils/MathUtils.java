package com.xuan.utils;

import java.math.BigDecimal;

/**
 * 由于 Java 的简单类型不能够精确的对浮点数进行运算，此工具类提供精确的浮点数运算，包括加减乘除和四舍五入
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:53:36 $
 */
public abstract class MathUtils {

    // 默认除法运算精度
    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = createBigDecimal(v1);
        BigDecimal b2 = createBigDecimal(v2);

        return b1.add(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b1 = createBigDecimal(v1);
        BigDecimal b2 = createBigDecimal(v2);

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 判断 double 值是否非法，值为 Infinite 或者 NaN 即表示非法。
     * 
     * @param v
     *            doube 值
     * @return 如果值为 Infinite 或者 NaN 则返回 <code>true</code>，否则返回 <code>false</code>。
     */
    public static boolean isInvalidDouble(double v) {
        return Double.isInfinite(v) || Double.isNaN(v);
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = createBigDecimal(v1);
        BigDecimal b2 = createBigDecimal(v2);

        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。如果 v 是非法的，则原样返回。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        if (isInvalidDouble(v)) {
            return v;
        }

        BigDecimal b = createBigDecimal(v);
        return b.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = createBigDecimal(v1);
        BigDecimal b2 = createBigDecimal(v2);

        return b1.subtract(b2).doubleValue();
    }

    /**
     * 采用 BigDecimal 的字符串构造器进行初始化。
     * 
     * @param v
     *            double 值
     * @return BigDecimal 对象
     */
    private static BigDecimal createBigDecimal(double v) {
        return new BigDecimal(Double.toString(v));
    }

}
