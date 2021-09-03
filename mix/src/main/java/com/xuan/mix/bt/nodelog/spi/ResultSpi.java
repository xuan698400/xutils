package com.xuan.mix.bt.nodelog.spi;

import com.xuan.mix.bt.nodelog.model.NodeLogResult;

/**
 * @author xuan
 * @since 2021/9/3
 */
public interface ResultSpi {

    /**
     * 从返回结果中解析成功状态
     *
     * @param result
     * @return
     */
    NodeLogResult parseResult(Object result);
}
