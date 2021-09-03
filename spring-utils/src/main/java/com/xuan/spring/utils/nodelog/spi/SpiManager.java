package com.xuan.spring.utils.nodelog.spi;

import com.xuan.spring.utils.nodelog.spi.impl.AsyncPrinterSpi;
import com.xuan.spring.utils.nodelog.spi.impl.DefaultResultSpi;
import com.xuan.spring.utils.nodelog.spi.impl.DefaultTraceSpi;
import com.xuan.spring.utils.nodelog.spi.impl.Slf4jLoggerSpi;

/**
 * 可扩展点管理
 *
 * @author xuan
 * @since 2021/9/3
 */
public class SpiManager {

    private static LoggerSpi loggerSpi = new Slf4jLoggerSpi();

    private static ResultSpi resultSpi = new DefaultResultSpi();

    private static PrinterSpi printerSpi = new AsyncPrinterSpi();

    private static TraceSpi traceSpi = new DefaultTraceSpi();

    public static LoggerSpi getLoggerSpi() {
        return loggerSpi;
    }

    public static ResultSpi getResultSpi() {
        return resultSpi;
    }

    public static PrinterSpi getPrinterSpi() {
        return printerSpi;
    }

    public static void registerLoggerSpi(LoggerSpi loggerSpi) {
        SpiManager.loggerSpi = loggerSpi;
    }

    public static void registerResultSpi(ResultSpi resultSpi) {
        SpiManager.resultSpi = resultSpi;
    }

    public static void registerPrinterSpi(PrinterSpi printerSpi) {
        SpiManager.printerSpi = printerSpi;
    }

    public static TraceSpi getTraceSpi() {
        return traceSpi;
    }

    public static void registerTraceSpi(TraceSpi traceSpi) {
        SpiManager.traceSpi = traceSpi;
    }

}
