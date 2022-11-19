package com.xuan.mix.jdk.spi;

/**
 * @author xuan
 * @since 2022/11/18
 */
public interface ExtSpi {

    boolean support(String bizCode);

    String execute(String params);
}
