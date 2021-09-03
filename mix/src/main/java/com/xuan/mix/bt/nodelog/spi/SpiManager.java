package com.xuan.mix.bt.nodelog.spi;

import com.xuan.mix.bt.nodelog.spi.impl.DefaultPrinterSpi;
import com.xuan.mix.bt.nodelog.spi.impl.DefaultTraceSpi;
import com.xuan.mix.bt.nodelog.spi.impl.DefaultLoggerSpi;
import com.xuan.mix.bt.nodelog.spi.impl.DefaultResultSpi;
import com.xuan.mix.bt.nodelog.spi.impl.DefaultSerializeSpi;

/**
 * 可扩展点管理
 *
 * @author xuan
 * @since 2021/9/3
 */
public class SpiManager {

    private static LoggerSpi loggerSpi = new DefaultLoggerSpi();

    private static ResultSpi resultSpi = new DefaultResultSpi();

    private static PrinterSpi printerSpi = new DefaultPrinterSpi();

    private static TraceSpi traceSpi = new DefaultTraceSpi();

    private static SerializeSpi serializeSpi = new DefaultSerializeSpi();

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

    public static SerializeSpi getSerializeSpi() {
        return serializeSpi;
    }

    public static void registerSerializeSpi(SerializeSpi serializeSpi) {
        SpiManager.serializeSpi = serializeSpi;
    }
}
