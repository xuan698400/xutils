package com.xuan.spring.utils.extp.model;

import java.util.Set;

/**
 * @author xuan
 * @since 2021/7/30
 */
public interface Plugin {

    /**
     * 扩展点实现所支持的业务
     *
     * @return 所支持的业务集合
     */
    Set<String> supportedBizCodes();
}
