package com.xuan.xutils.tools.cmd.controller.model;

import java.util.List;
import java.util.Map;

/**
 * 执行结果
 *
 * @author xuan
 * @since 2023/8/24
 */
public class ExecInfo {

    /**
     * 结果信息
     */
    private List<String> resultList;

    /**
     * 本机器IP
     */
    private String ip;

    /**
     * 所有文件名中最长的长度
     */
    private int maxFileNameLength;

    /**
     * 文件名类型MAP，key=文件名，value=文件类型
     */
    private Map<String, String> fileTypeMap;

    /**
     * 操作用户
     */
    private String userName;

    /**
     * 操作路径
     */
    private String path;

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getMaxFileNameLength() {
        return maxFileNameLength;
    }

    public void setMaxFileNameLength(int maxFileNameLength) {
        this.maxFileNameLength = maxFileNameLength;
    }

    public Map<String, String> getFileTypeMap() {
        return fileTypeMap;
    }

    public void setFileTypeMap(Map<String, String> fileTypeMap) {
        this.fileTypeMap = fileTypeMap;
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
