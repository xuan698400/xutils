package com.xuan.rbac.service.model;

import java.util.Map;

/**
 * @author xuan
 * @since 2020/11/27
 */
public class BaseDomain {

    private Long id;

    private String bizCode;

    private Map<String, String> featureMap;

    private Integer status;

    @Override
    public String toString() {
        return "BaseDomain{" +
            "id=" + id +
            ", bizCode='" + bizCode + '\'' +
            ", featureMap=" + featureMap +
            ", status=" + status +
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

    public Map<String, String> getFeatureMap() {
        return featureMap;
    }

    public void setFeatureMap(Map<String, String> featureMap) {
        this.featureMap = featureMap;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
