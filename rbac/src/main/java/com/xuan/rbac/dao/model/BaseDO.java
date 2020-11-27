package com.xuan.rbac.dao.model;

import java.util.Date;

/**
 * @author xuan
 * @since 2020/11/27
 */
public class BaseDO {

    private Long id;

    private String bizCode;

    private Integer version;

    private String feature;

    private Integer status;

    private Integer type;

    private Date gmtCreate;

    private Date gmtModified;

    private String creator;

    private String modifier;

    @Override
    public String toString() {
        return "BaseDO{" +
            "id=" + id +
            ", bizCode='" + bizCode + '\'' +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
            ", creator='" + creator + '\'' +
            ", modifier='" + modifier + '\'' +
            ", version=" + version +
            ", feature='" + feature + '\'' +
            ", status=" + status +
            ", type=" + type +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
