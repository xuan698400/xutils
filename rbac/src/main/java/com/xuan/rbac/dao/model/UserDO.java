package com.xuan.rbac.dao.model;

/**
 * @author xuan
 * @since 2020/11/27
 */
public class UserDO extends BaseDO {

    private String username;

    private String byName;

    private String realName;

    private String phone;

    private String email;

    private String icon;

    private String password;

    @Override
    public String toString() {
        return super.toString() + ", UserDO{" +
            "username='" + username + '\'' +
            ", byName='" + byName + '\'' +
            ", realName='" + realName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", icon='" + icon + '\'' +
            ", password='" + password + '\'' +
            '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getByName() {
        return byName;
    }

    public void setByName(String byName) {
        this.byName = byName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
