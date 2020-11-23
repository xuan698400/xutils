package com.xuan.user.dao.model;

import java.util.List;

import com.xuan.user.model.request.BaseRequest;

/**
 * @author xuan
 * @since 2020/2/28
 */
public class UserQueryParams extends BaseRequest {

    private String bizCode;

    private String email;

    private String username;

    private List<Integer> statusList;

    @Override
    public String getBizCode() {
        return bizCode;
    }

    @Override
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

}
