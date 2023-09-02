package com.xuan.xutils.base.log;

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

        Log logLine = new Log("test_name");
        logLine.setClazzName("clazzName123");
        logLine.setMethodName("methodName123");
        logLine.setTraceId("traceId123");
        logLine.setParams(new Object[] {objTest, objTest2});
        logLine.setResult(objTest);
        logLine.setStatus(logLine.STATUS_SUCCESS);
        logLine.setResultCode("resultCode123");
        logLine.setResultMsg("resultMsg123");
        logLine.setCostTime(1000L);
        logLine.setThrowable(null);
        logLine.setExt("ext123");
        log.info(logLine);
    }

}
