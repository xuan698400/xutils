package com.xuan.xutils.tools.cmd.core;

import java.util.List;
import java.util.Map;

/**
 * @author xuan
 * @since 2023/8/24
 */
public class CommandResult {

    /**
     * true/false
     */
    private boolean success;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 结果信息，一条记录表示一行返回
     */
    private List<String> resultLines;

    /**
     * 本机器IP
     */
    private String ip;

    /**
     * 操作用户
     */
    private String userName;

    /**
     * 操作路径
     */
    private String path;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getResultLines() {
        return resultLines;
    }

    public void setResultLines(List<String> resultLines) {
        this.resultLines = resultLines;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
