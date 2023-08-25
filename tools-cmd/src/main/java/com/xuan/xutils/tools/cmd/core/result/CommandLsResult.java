package com.xuan.xutils.tools.cmd.core.result;

import java.util.Map;

import com.xuan.xutils.tools.cmd.core.CommandResult;

/**
 * ls命令返回的是文件列表，所以需要额外的返回信息
 *
 * @author xuan
 * @since 2023/8/24
 */
public class CommandLsResult extends CommandResult {

    /**
     * 所有文件名中最长的长度
     */
    private int maxFileNameLength;

    /**
     * 文件名类型MAP，key=文件名，value=文件类型
     */
    private Map<String, String> fileTypeMap;

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
}
