package com.xuan.mix.bt.nodelog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xuan.mix.bt.nodelog.spi.SpiManager;

/**
 * 日志输出格式如下：
 * |nodeName|clazzName|methodName|traceId|rpcId|params|result|status|resultCode|resultMsg|costTime|throwable|ext
 *
 * @author xuan
 * @since 2019/9/24
 */
public class LogLine {
    /**
     * 单元分隔符，一定要选择业务不会使用的字符，例如“|”、“,”、“$”，
     */
    public static final char SEPARATOR = '|';
    /**
     * 默认占位符，在字段没有设置数据时使用，可以用空字符串
     */
    public static final String PLACEHOLDER = "-";
    /**
     * 成功返回标识
     */
    public static final String STATUS_SUCCESS = "Y";
    /**
     * 错误返回标识
     */
    public static final String STATUS_FAIL = "N";
    /**
     * eagle eye traceId 官方 MDC 名称
     */
    public static final String TRACE_ID_EAGLE_EYE = "EAGLEEYE_TRACE_ID";
    /**
     * eagle eye rpcId 官方 MDC 名称
     */
    public static final String RPC_ID_EAGLE_EYE = "EAGLEEYE_RPC_ID";
    /**
     * 程序未捕获的未知异常
     */
    public static final String ERROR_CODE_UNKONW = "ERROR_CODE_UNKONW";
    /**
     * 解析返回结果是null
     */
    public static final String ERROR_CODE_RESULT_NULL = "ERROR_CODE_RESULT_NULL";
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 类名
     */
    private String clazzName;
    /**
     * 方法
     */
    private String methodName;
    /**
     * traceId
     */
    private String traceId;
    /**
     * rpcId
     */
    private String rpcId;
    /**
     * 切面直接获取的请求参数
     */
    private Object[] params;
    /**
     * 切面直接获取到的返回结果
     */
    private Object result;
    /**
     * 请求相应状态
     */
    private String status;
    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 错误信息
     */
    private String resultMsg;
    /**
     * 执行花费时间
     */
    private long costTime;
    /**
     * 如果有异常，存储异常信息
     */
    private Throwable throwable;
    /**
     * 自定义扩展信息
     */
    private String ext;

    public LogLine(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getRpcId() {
        return rpcId;
    }

    public void setRpcId(String rpcId) {
        this.rpcId = rpcId;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getNodeName()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getClazzName()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getMethodName()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getTraceId()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getRpcId()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNullObj(getParams()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNullObj(getResult()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getStatus()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getResultCode()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(getResultMsg()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNull(String.valueOf(getCostTime())));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNullObj(getThrowable()));
        sb.append(LogLine.SEPARATOR);
        sb.append(notNullObj(getExt()));
        sb.append(LogLine.SEPARATOR);
        return sb.toString();
    }

    private String notNull(String str) {
        if (null == str) {
            return LogLine.PLACEHOLDER;
        }
        return str;
    }

    private String notNullObj(Object obj) {
        if (null == obj) {
            return LogLine.PLACEHOLDER;
        }

        if (obj instanceof String) {
            return (String)obj;
        } else if (obj instanceof Byte) {
            return String.valueOf((byte)obj);
        } else if (obj instanceof Short) {
            return String.valueOf((short)obj);
        } else if (obj instanceof Boolean) {
            return String.valueOf((boolean)obj);
        } else if (obj instanceof Integer) {
            return String.valueOf((int)obj);
        } else if (obj instanceof Long) {
            return String.valueOf((long)obj);
        } else if (obj instanceof Double) {
            return String.valueOf((double)obj);
        } else if (obj instanceof Float) {
            return String.valueOf((float)obj);
        } else if (obj instanceof Date) {
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format((Date)obj);
        } else {
            return SpiManager.getSerializeSpi().toString(obj);
        }
    }

}
