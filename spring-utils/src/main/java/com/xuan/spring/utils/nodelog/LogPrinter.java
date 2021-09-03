package com.xuan.spring.utils.nodelog;

import java.util.concurrent.ThreadLocalRandom;

import com.xuan.spring.utils.nodelog.config.NodeLogConfig;
import com.xuan.spring.utils.nodelog.config.NodeLogConfigManager;
import com.xuan.spring.utils.nodelog.model.LogLine;
import com.xuan.spring.utils.nodelog.spi.SpiManager;

/**
 * client内部日志输出异步化
 *
 * @author xuan
 * @since 2019/9/24
 */
public class LogPrinter {

    private long startTime;
    private LogLine logLine;
    private NodeLogConfig config = new NodeLogConfig();

    public LogLine getLogLine() {
        return logLine;
    }

    private LogPrinter(LogLine logLine, long startTime) {
        this.startTime = startTime;
        this.logLine = logLine;
    }

    public static LogPrinter start(String nodeName) {
        LogLine logLine = new LogLine();
        logLine.setNodeName(nodeName);
        return new LogPrinter(logLine, System.currentTimeMillis());
    }

    public LogPrinter setTraceId(String traceId) {
        if (null != getLogLine()) {
            getLogLine().setTraceId(traceId);
        }
        return this;
    }

    public LogPrinter setRpcId(String rpcId) {
        if (null != getLogLine()) {
            getLogLine().setRpcId(rpcId);
        }
        return this;
    }

    public LogPrinter setClazzName(String clazzName) {
        if (null != getLogLine()) {
            getLogLine().setClazzName(clazzName);
        }
        return this;
    }

    public LogPrinter setMethodName(String methodName) {
        if (null != getLogLine()) {
            getLogLine().setMethodName(methodName);
        }
        return this;
    }

    public LogPrinter setStatus(String status) {
        if (null != getLogLine()) {
            getLogLine().setStatus(status);
        }
        return this;
    }

    public LogPrinter setResultCode(String resultCode) {
        if (null != getLogLine()) {
            getLogLine().setResultCode(resultCode);
        }
        return this;
    }

    public LogPrinter setResultMsg(String resultMsg) {
        if (null != getLogLine()) {
            getLogLine().setResultMsg(resultMsg);
        }
        return this;
    }

    public LogPrinter setThrowable(Throwable throwable) {
        if (null != getLogLine() && config.getPrintStack()) {
            getLogLine().setThrowable(throwable);
        }
        return this;
    }

    public LogPrinter setParams(Object[] params) {
        if (null != getLogLine() && config.getPrintParams()) {
            getLogLine().setParams(params);
        }
        return this;
    }

    public LogPrinter setResult(Object result) {
        if (null != getLogLine() && config.getPrintResult()) {
            getLogLine().setResult(result);
        }
        return this;
    }

    public LogPrinter setExt(String ext) {
        if (null != getLogLine()) {
            getLogLine().setExt(ext);
        }
        return this;
    }

    public void end(String status, String resultCode, String resultMsg, Throwable throwable, Object[] params,
        Object result, String ext) {
        if (null != getLogLine()) {
            getLogLine()
                .setTraceId(getTraceId())
                .setRpcId(getRpcId())
                .setStatus(status)
                .setResultCode(resultCode)
                .setResultMsg(resultMsg)
                .setThrowable(throwable)
                .setParams(params)
                .setResult(result)
                .setExt(ext);

            doEnd();
        }
    }

    public void end() {
        if (null != getLogLine()) {
            doEnd();
        }
    }

    private void doEnd() {
        if (!canLog(getLogLine().getNodeName(), getLogLine().getStatus())) {
            return;
        }
        getLogLine().setCostTime((System.currentTimeMillis() - startTime));
        SpiManager.getPrinterSpi().print(SpiManager.getLoggerSpi(), getLogLine().toString());
    }

    private boolean canLog(String nodeName, String status) {
        NodeLogConfig nodeLogConfig = NodeLogConfigManager.getNodeLogConfig(nodeName);

        if (isFailCasePrint(nodeLogConfig, status)) {
            return true;
        }

        if (nodeLogConfig.getSample() == 0) {
            return false;
        }

        if (nodeLogConfig.getSample() >= 10000) {
            return true;
        }

        return ThreadLocalRandom.current().nextInt(10000) < nodeLogConfig.getSample();
    }

    private String getTraceId() {
        return SpiManager.getTraceSpi().getTraceId();
    }

    private String getRpcId() {
        return SpiManager.getTraceSpi().getRpcId();
    }

    private boolean isFailCasePrint(NodeLogConfig nodeLogConfig, String status) {
        return nodeLogConfig.getPrintIfResultFail() && LogLine.STATUS_FAIL.equals(status);
    }

}
