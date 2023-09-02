package com.xuan.xutils.design.spi;

/**
 * 定义需要扩展的接口
 *
 * @author xuan
 * @since 2022/11/18
 */
public interface ExtSpi {

    /**
     * 子类实现，判断是否需要处理
     *
     * @param bizCode 业务码
     * @return true/false
     */
    boolean support(String bizCode);

    /**
     * 执行业务逻辑
     *
     * @param params 入参
     * @return 返参
     */
    String execute(String params);
}
