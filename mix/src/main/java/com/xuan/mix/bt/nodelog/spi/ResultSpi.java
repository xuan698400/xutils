package com.xuan.mix.bt.nodelog.spi;

import com.xuan.mix.bt.nodelog.model.NodeLogResult;

/**
 * 结果解析，输出的日志会结构化status、resultCode、resultMsg
 * 由于不同的业务返回的模型不一样，所以实现该扩展可以适配返回的结果，使日志可以结构化输出
 *
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
