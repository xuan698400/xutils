package com.xuan.spring.utils.nodelog.spi.impl;

import com.xuan.spring.utils.nodelog.model.NodeLogResult;
import com.xuan.spring.utils.nodelog.spi.ResultSpi;

/**
 * 默认实现，因无法预知业务结果模型，所以这里统一解析为成功
 *
 * @author xuan
 * @since 2021/9/3
 */
public class DefaultResultSpi implements ResultSpi {

    @Override
    public NodeLogResult parseResult(Object result) {
        NodeLogResult nodeLogResult = new NodeLogResult();
        nodeLogResult.setSuccess(true);
        nodeLogResult.setResultCode("-");
        nodeLogResult.setResultMsg("-");
        return nodeLogResult;
    }

}
