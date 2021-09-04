package com.xuan.spring.utils.nodelog.testservice;

import com.xuan.spring.utils.nodelog.aspect.NodeLog;
import org.springframework.stereotype.Service;

/**
 * @author xuan
 * @since 2021/9/4
 */
@Service
public class NodeLogServiceImpl implements NodeLogService {

    @Override
    @NodeLog(printParams = false)
    public TestResult callTest(TestRequest request) {
        TestResult ddd = new TestResult();
        ddd.setSuccess(true);
        ddd.setCode("ok");
        ddd.setMsg("成功");
        return ddd;
    }

}
