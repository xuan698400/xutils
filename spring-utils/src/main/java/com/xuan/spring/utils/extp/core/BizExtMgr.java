package com.xuan.spring.utils.extp.core;

import java.util.List;

import com.xuan.spring.utils.extp.model.BizExt;

/**
 * 业务扩展点管理器
 *
 * @author xuan
 * @since 2019/11/5
 */
public interface BizExtMgr {

    /**
     * 根据业务类型和扩展点接口Class获取扩展点实例
     *
     * @param bizCode      业务身份
     * @param extInterface 扩展点接口Class
     * @return 扩展点实例
     */
    List<BizExt> getBizExts(String bizCode, Class extInterface);

}
