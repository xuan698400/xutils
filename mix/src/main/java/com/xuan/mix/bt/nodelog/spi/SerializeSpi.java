package com.xuan.mix.bt.nodelog.spi;

/**
 * 打印日志时，难免需要把对象转成String打印，实现该扩展可以自定义序列化
 * 默认的是使用了Object.toString方法
 *
 * @author xuan
 * @since 2021/9/3
 */
public interface SerializeSpi {

    /**
     * 对象转String
     *
     * @param obj 对象
     * @return 输出String
     */
    String toString(Object obj);
}
