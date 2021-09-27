package com.xuan.mix.domain.common;

import com.xuan.mix.common.exception.BizException;
import com.xuan.mix.common.invoker.BizCommond;
import com.xuan.mix.common.invoker.BizCommondResult;
import com.xuan.mix.common.invoker.BizCommondResultBuilder;

/**
 * @author xuan
 * @since 2021/9/27
 */
public class BaseDomainService {

    public static <T> BizCommondResult<T> call(BizCommond<T> commond) {
        try {
            return commond.call();
        } catch (BizException be) {
            return BizCommondResultBuilder.buildFail(be.getErrCode(), be.getMessage());
        } catch (Exception e) {
            return BizCommondResultBuilder.buildFail(BizException.DEFAULT_ERR_CODE, e.getMessage());
        }
    }

}
