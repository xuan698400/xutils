package com.extp.framework.core.plugin.model;

import java.util.List;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class Plugin {

    private String code;

    private String name;

    private List<ExtImpl> extImplList;

    private List<BizCode> bizCodeList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExtImpl> getExtImplList() {
        return extImplList;
    }

    public void setExtImplList(List<ExtImpl> extImplList) {
        this.extImplList = extImplList;
    }

    public List<BizCode> getBizCodeList() {
        return bizCodeList;
    }

    public void setBizCodeList(List<BizCode> bizCodeList) {
        this.bizCodeList = bizCodeList;
    }
}
