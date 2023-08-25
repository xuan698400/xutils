package com.xuan.xutils.tools.cmd.core.common;

/**
 * 文件枚举
 *
 * @author xuan
 * @since 2023/8/24
 */
public enum FileTypeEnum {

    //
    SH("sh", "可执行文件"),
    DIR("dir", "文件夹"),
    FILE("file", "文件");

    private String code;

    private String name;

    FileTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据文件名获取文件类型，如果文件名空或者未匹配到的统一返回文件类型
     *
     * @param fileName 文件名
     * @return 文件名类型code
     */
    public String getFileTypeCodeByFileName(String fileName) {
        if (null == fileName) {
            return FILE.getCode();
        }
        for (FileTypeEnum fileTypeEnum : FileTypeEnum.values()) {
            if (fileName.endsWith(Constants.DOT + fileTypeEnum.getCode())) {
                return fileTypeEnum.getCode();
            }
        }
        return FILE.getCode();
    }

}
