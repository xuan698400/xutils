package com.xuan.spring.utils.extp.model;

import java.util.Set;

/**
 * 业务扩展点模型
 *
 * @author xuan
 * @since 2019/11/5
 */
public interface BizExt {

    String SUPPORTED_BIZ_CODE_ALL = "_ALL_";
    
    /**
     * 执行优先级
     *
     * @return 参考：com.xuan.spring.utils.extp.model.PriorityEnum
     */
    default int priority() {
        return PriorityEnum.MIDDLE.getValue();
    }

    /**
     * 所支持的业务，SUPPORTED_BIZ_CODE_ALL表示支持所有业务
     *
     * @return set
     */
    Set<String> supportedBizCodes();

    /**
     * 具体扩展点类Class
     *
     * @return 扩展点接口Class
     */
    Class getExtClass();

}
