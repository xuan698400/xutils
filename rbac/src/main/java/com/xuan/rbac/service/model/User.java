package com.xuan.rbac.service.model;

/**
 * @author xuan
 * @since 2020/11/27
 */
public class User extends BaseDomain {

    private String uniqueCode;

    private String byName;

    private String realName;

    private String phone;

    private String email;

    @Override
    public String toString() {
        return super.toString() + ", User{" +
            "uniqueCode='" + uniqueCode + '\'' +
            ", byName='" + byName + '\'' +
            ", realName='" + realName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
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

}
