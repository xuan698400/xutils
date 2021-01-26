package com.xuan.common.invoker;

import com.xuan.common.exception.BizException;
import com.xuan.common.exception.BizExceptionCodeEnum;
import com.xuan.common.model.BizResult;
import com.xuan.common.model.BizResultBuilder;

/**
 * 使用该call方法包装，可以进行手动AOP拦截
 *
 * @author xuan
 * @since 2021/1/19
 */
public class BizInvoker {

    public static <T> BizResult<T> call(BizCommond<T> commond) {
        try {
            return commond.call();
        } catch (BizException be) {
            return BizResultBuilder.buildFail(be.getCode(), be.getMsg());
        } catch (Exception e) {
            return BizResultBuilder.buildFail(BizExceptionCodeEnum.UNKNOWN_EXCEPTION.getCode(), e.getMessage());
        }
    }

}
