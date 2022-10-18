package com.xuan.mix.biz.account;

/**
 * @author xuan
 * @since 2022/10/18
 */
public class Account {

    private String loginName;

    private String password;

    private String token;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
