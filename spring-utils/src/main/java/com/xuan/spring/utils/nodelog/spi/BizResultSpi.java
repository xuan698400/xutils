package com.xuan.spring.utils.nodelog.spi;

import com.xuan.mix.bt.nodelog.model.NodeLogResult;
import com.xuan.mix.bt.nodelog.spi.ResultSpi;
import com.xuan.spring.utils.nodelog.testservice.TestResult;

/**
 * @author xuan
 * @since 2021/9/4
 */
public class BizResultSpi implements ResultSpi {
    @Override
    public NodeLogResult parseResult(Object result) {
        NodeLogResult r = new NodeLogResult();

        if (null == result) {
            r.setSuccess(false);
            r.setResultCode("result_null");
            r.setResultMsg("结果是空");
            return r;
        }

        if (result instanceof TestResult) {
            r.setSuccess(((TestResult)result).isSuccess());
            r.setResultCode(((TestResult)result).getCode());
            r.setResultMsg(((TestResult)result).getMsg());
            return r;
        }

        r.setSuccess(true);
        r.setResultCode("not_found_result_type");
        r.setResultMsg("未找到匹配结果类型");
        return r;
    }

}
