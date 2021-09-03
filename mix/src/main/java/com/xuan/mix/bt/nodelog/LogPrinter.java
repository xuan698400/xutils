package com.xuan.mix.bt.nodelog;

import java.util.concurrent.ThreadLocalRandom;

import com.xuan.mix.bt.nodelog.config.NodeLogConfig;
import com.xuan.mix.bt.nodelog.config.NodeLogConfigManager;
import com.xuan.mix.bt.nodelog.model.LogLine;
import com.xuan.mix.bt.nodelog.spi.SpiManager;

/**
 * client内部日志输出异步化
 *
 * @author xuan
 * @since 2019/9/24
 */
public class LogPrinter {

    private long startTime;
    private LogLine logLine;
    private NodeLogConfig config;

    private LogPrinter() {
    }

    public static LogPrinter start(String nodeName) {
        return LogPrinter.start(nodeName, NodeLogConfigManager.DEFAULT_CONFIG);
    }

    public static LogPrinter start(String nodeName, NodeLogConfig config) {
        LogPrinter logPrinter = new LogPrinter();
        logPrinter.startTime = System.currentTimeMillis();
        logPrinter.logLine = new LogLine(nodeName);
        logPrinter.config = config;
        return logPrinter;
    }

    public LogPrinter setClazzName(String clazzName) {
        this.logLine.setClazzName(clazzName);
        return this;
    }

    public LogPrinter setMethodName(String methodName) {
        this.logLine.setMethodName(methodName);
        return this;
    }

    public LogPrinter setParams(Object[] params) {
        if (config.isPrintParams()) {
            this.logLine.setParams(params);
        }
        return this;
    }

    public LogPrinter setResult(Object result) {
        if (config.isPrintResult()) {
            this.logLine.setResult(result);
        }
        return this;
    }

    public LogPrinter setStatus(String status) {
        this.logLine.setStatus(status);
        return this;
    }

    public LogPrinter setResultCode(String resultCode) {
        this.logLine.setResultCode(resultCode);
        return this;
    }

    public LogPrinter setResultMsg(String resultMsg) {
        this.logLine.setResultMsg(resultMsg);
        return this;
    }

    public LogPrinter setThrowable(Throwable throwable) {
        this.logLine.setThrowable(throwable);
        return this;
    }

    public LogPrinter setExt(String ext) {
        this.logLine.setExt(ext);
        return this;
    }

    public void end() {

        //补充系统默认可以获取的参数
        this.logLine.setTraceId(SpiManager.getTraceSpi().getTraceId());
        this.logLine.setRpcId(SpiManager.getTraceSpi().getRpcId());
        this.logLine.setCostTime(System.currentTimeMillis() - startTime);

        //获取配置，优先级以配置的为准
        NodeLogConfig temp = NodeLogConfigManager.getConfig(this.logLine.getNodeName());
        if (null != temp) {
            this.config = temp;
        }
        if (!canLog(this.logLine.getNodeName(), this.logLine.getStatus())) {
            return;
        }
        SpiManager.getPrinterSpi().print(SpiManager.getLoggerSpi(), this.logLine.toString());
    }

    private boolean canLog(String nodeName, String status) {
        if (isFailCasePrint(this.config, status)) {
            return true;
        }

        if (this.config.getSample() == 0) {
            return false;
        }

        if (this.config.getSample() >= NodeLogConfig.SAMPLE_DENOMINATOR) {
            return true;
        }

        return ThreadLocalRandom.current().nextInt(NodeLogConfig.SAMPLE_DENOMINATOR) < this.config.getSample();
    }

    private boolean isFailCasePrint(NodeLogConfig config, String status) {
        return config.getPrintIfResultFail() && LogLine.STATUS_FAIL.equals(status);
    }

}
