package com.xuan.xutils.base.log;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 日志输出格式，输出日志时，最好按一定的规范输出，后续使用日志分析工具时，可以方便查询分割查找
 *
 * 格式：|logName|clazzName|methodName|traceId|params|result|status|resultCode|resultMsg|costTime|throwable|ext
 *
 * @author xuan
 * @since 2023/5/10
 */
public class Log {

    /**
     * 分隔符
     */
    public static final char SP = '|';

    /**
     * NULL占位符
     */
    public static final String NULL = "NULL";

    /**
     * 成功标识
     */
    public static final String STATUS_SUCCESS = "Y";

    /**
     * 错误标识
     */
    public static final String STATUS_FAIL = "N";

    /**
     * 未知异常
     */
    public static final String ERROR_CODE_UNKNOWN = "ERROR_CODE_UNKNOWN";

    /**
     * 结果NULL
     */
    public static final String ERROR_CODE_RESULT_NULL = "ERROR_CODE_RESULT_NULL";

    /**
     * 日志名称
     */
    private String logName;

    /**
     * 类名
     */
    private String clazzName;

    /**
     * 方法
     */
    private String methodName;

    /**
     * 全局追踪ID
     */
    private String traceId;

    /**
     * 入参
     */
    private Object[] params;

    /**
     * 结果
     */
    private Object result;

    /**
     * 请求相应状态
     */
    private String status;

    /**
     * 结果码
     */
    private String resultCode;

    /**
     * 结果提示
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

    public Log(String logName) {
        this.logName = logName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Log.SP);
        sb.append(notNull(this.logName));
        sb.append(Log.SP);
        sb.append(notNull(this.clazzName));
        sb.append(Log.SP);
        sb.append(notNull(this.methodName));
        sb.append(Log.SP);
        sb.append(notNull(this.traceId));
        sb.append(Log.SP);
        sb.append(notNull(this.params));
        sb.append(Log.SP);
        sb.append(notNull(this.result));
        sb.append(Log.SP);
        sb.append(notNull(this.status));
        sb.append(Log.SP);
        sb.append(notNull(this.resultCode));
        sb.append(Log.SP);
        sb.append(notNull(this.resultMsg));
        sb.append(Log.SP);
        sb.append(notNull(String.valueOf(this.costTime)));
        sb.append(Log.SP);
        sb.append(notNull(this.throwable));
        sb.append(Log.SP);
        sb.append(notNull(this.ext));
        sb.append(Log.SP);
        return sb.toString();
    }

    private String notNull(Object obj) {
        if (null == obj) {
            return Log.NULL;
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
        } else if (obj instanceof Object[]) {
            return Arrays.toString((Object[])obj);
        } else {
            return obj.toString();
        }
    }

}
