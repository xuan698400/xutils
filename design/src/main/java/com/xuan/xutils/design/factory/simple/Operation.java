package com.xuan.xutils.design.factory.simple;

/**
 * 计算操作接口
 *
 * @author xuan
 * @since 2021/8/18
 */
public interface Operation {
    /**
     * 计算
     *
     * @param num1 数1
     * @param num2 数2
     * @return 结果
     */
    double compute(double num1, double num2);
}
