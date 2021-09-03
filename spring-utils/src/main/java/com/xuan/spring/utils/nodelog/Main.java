package com.xuan.spring.utils.nodelog;

import com.xuan.mix.bt.nodelog.LogPrinter;
import com.xuan.mix.bt.nodelog.spi.SpiManager;
import com.xuan.spring.utils.nodelog.spi.Slf4jLoggerSpi;

/**
 * @author xuan
 * @since 2021/9/3
 */
public class Main {

    public static void main(String[] args) {
        SpiManager.registerLoggerSpi(new Slf4jLoggerSpi());

        LogPrinter logPrinter1 = LogPrinter.start("test1");
        try {
            String d = null;
            doSleep();
            logPrinter1
                .setClazzName("clazzName11")
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
    }

    private static void doSleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //Ignore
        }
    }

}
