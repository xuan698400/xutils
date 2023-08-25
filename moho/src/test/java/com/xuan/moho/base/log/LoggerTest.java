package com.xuan.moho.base.log;

import org.junit.Test;

/**
 * |logName|clazzName|methodName|traceId|params|result|status|resultCode|resultMsg|costTime|throwable|ext
 *
 * @author xuan
 * @since 2023/5/12
 */
public class LoggerTest {

    private final static Logger log = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
        ObjTest objTest = new ObjTest();
        objTest.setName("testNameObj");
        objTest.setAge(18L);

        ObjTest objTest2 = new ObjTest();
        objTest2.setName("testNameObj222");
        objTest2.setAge(30L);

        LogModel logModel = new LogModel("test_name");
        logModel.setClazzName("clazzName123");
        logModel.setMethodName("methodName123");
        logModel.setTraceId("traceId123");
        logModel.setParams(new Object[] {objTest, objTest2});
        logModel.setResult(objTest);
        logModel.setStatus(LogModel.STATUS_SUCCESS);
        logModel.setResultCode("resultCode123");
        logModel.setResultMsg("resultMsg123");
        logModel.setCostTime(1000L);
        logModel.setThrowable(null);
        logModel.setExt("ext123");
        log.info(logModel);
    }

}
