package com.xuan.spring.utils.extp.model;

/**
 * @author xuan
 * @since 2021/7/30
 */
public interface ExtensionPoint {

    /**
     * 扩展点的优先级
     *
     * @return 参考：com.xuan.spring.utils.extp.model.PriorityEnum
     */
    default int priority() {
        return PriorityEnum.MIDDLE.getValue();
    }

}
