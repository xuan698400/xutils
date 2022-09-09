package com.xuan.hitools.yuntai;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xuan
 * @since 2022/9/7
 */
public class YunTaiFile {
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Long id;

    private String name;

    private String fileType;

    private String contentType;

    private String content;

    private Long parentId;

    private String tenant;

    private Date createTime;

    private Date modifyTime;

    private String createTimeFormat;

    private String modifyTimeFormat;

    private String creator;

    private String modifier;

    public static YunTaiFile buildFile(String name, String fileType, Long parentId) {
        YunTaiFile yunTaiFile = new YunTaiFile();
        yunTaiFile.setName(name);
        yunTaiFile.setFileType(fileType);
        yunTaiFile.setContentType("db");
        yunTaiFile.setContent("");
        yunTaiFile.setParentId(parentId);
        yunTaiFile.setTenant("default");
        yunTaiFile.setCreateTime(new Date());
        yunTaiFile.setModifyTime(yunTaiFile.getCreateTime());
        yunTaiFile.setCreator("sys");
        yunTaiFile.setModifier("sys");
        return yunTaiFile;
    }

    public void formatTime() {
        this.createTimeFormat = (new SimpleDateFormat(TIME_FORMAT)).format(createTime);
        this.modifyTimeFormat = (new SimpleDateFormat(TIME_FORMAT)).format(modifyTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public String getModifyTimeFormat() {
        return modifyTimeFormat;
    }

    public void setModifyTimeFormat(String modifyTimeFormat) {
        this.modifyTimeFormat = modifyTimeFormat;
    }

}
