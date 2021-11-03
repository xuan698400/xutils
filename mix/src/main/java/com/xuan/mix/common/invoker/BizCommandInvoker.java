package com.xuan.mix.common.invoker;

import com.xuan.mix.common.exception.BizException;

/**
 * 使用该call方法包装，可以进行手动AOP拦截（改装版策略模式）
 *
 * @author xuan
 * @since 2021/1/19
 */
public class BizCommandInvoker {

    public static <T> BizCommandResult<T> call(BizCommand<T> commond) {
        try {
            T data = commond.execute();
            return BizCommandResultBuilder.buildSuccess(data);
        } catch (BizException be) {
            return BizCommandResultBuilder.buildFail(be.getCode(), be.getMessage());
        } catch (Exception e) {
            return BizCommandResultBuilder.buildFail(BizException.DEFAULT_ERR_CODE, e.getMessage());
        }
    }

}
