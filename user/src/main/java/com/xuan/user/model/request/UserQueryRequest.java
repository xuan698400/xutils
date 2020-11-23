package com.xuan.user.model.request;

import java.util.List;

/**
 * @author xuan
 * @since 2020/2/28
 */
public class UserQueryRequest extends PageRequest {
    private String email;

    private String username;

    private List<Integer> statusList;

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
