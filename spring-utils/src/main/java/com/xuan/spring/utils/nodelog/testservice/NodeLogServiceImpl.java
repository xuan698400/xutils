package com.xuan.spring.utils.nodelog.testservice;

import com.xuan.mix.bt.nodelog.annotation.NodeLog;

/**
 * @author xuan
 * @since 2021/9/4
 */
public class NodeLogServiceImpl implements NodeLogService {

    @Override
    @NodeLog
    public TestResult callTest(TestRequest request) {
        TestResult ddd = new TestResult();
        ddd.setSuccess(true);
        ddd.setCode("ok");
        ddd.setMsg("成功");
        return ddd;
    }

}
