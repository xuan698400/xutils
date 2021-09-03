package com.xuan.mix.bt.nodelog;

import com.xuan.mix.bt.nodelog.config.NodeLogConfig;

/**
 * @author xuan
 * @since 2021/9/3
 */
public class Main {

    public static void main(String[] args) {
        LogPrinter logPrinter1 = LogPrinter.start("test1");
        try {
            String d = null;
            doSleep();
            logPrinter1
                .setClazzName("clazzName1")
                .setMethodName("methodName1")
                .setParams(new Object[] {"11", "11"})
                .setStatus("Y")
                .setResultCode("rCode1")
                .setResultMsg("rMsg1")
                .setResult("result")
                .setExt("ext");
            d.length();
        } catch (Exception e) {
            logPrinter1.setThrowable(e);
        } finally {
            logPrinter1.end();
        }

        NodeLogConfig config = new NodeLogConfig();
        config.setSample(0);
        LogPrinter logPrinter2 = LogPrinter.start("test2", config);
        try {
            String d = null;
            doSleep();
            logPrinter2
                .setClazzName("clazzName2")
                .setMethodName("methodName2")
                .setParams(new Object[] {"22", "22"})
                .setStatus("Y")
                .setResultCode("rCode2")
                .setResultMsg("rMsg2")
                .setResult("result")
                .setExt("ext");
            d.length();
        } catch (Exception e) {
            logPrinter2.setThrowable(e);
        } finally {
            logPrinter2.end();
        }
    }

    private static void doSleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //Ignore
        }
    }

}
