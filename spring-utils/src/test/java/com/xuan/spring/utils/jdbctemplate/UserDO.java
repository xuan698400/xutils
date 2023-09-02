//package com.xuan.spring.utils.jdbctemplate;
//
//import java.util.Date;
//
//import com.xuan.mix.an.dao.common.DataModel;
//import com.xuan.mix.an.dao.common.NameValuePair;
//
///**
// * @author xuan
// * @since 2019/11/17
// */
//public class UserDO implements DataModel {
//
//    private Long id;
//
//    private String username;
//
//    private String name;
//
//    private String phone;
//
//    private String email;
//
//    private String icon;
//
//    private String password;
//
//    private Integer type;
//
//    private Integer status;
//
//    private String feature;
//
//    private String bizCode;
//
//    private Date createTime;
//
//    private Date modifyTime;
//
//    @Override
//    public String tableName() {
//        return "bw_user";
//    }
//
//    @Override
//    public NameValuePair primaryKey() {
//        return NameValuePair.of("id", id);
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public String getFeature() {
//        return feature;
//    }
//
//    public void setFeature(String feature) {
//        this.feature = feature;
//    }
//
//    public String getBizCode() {
//        return bizCode;
//    }
//
//    public void setBizCode(String bizCode) {
//        this.bizCode = bizCode;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getModifyTime() {
//        return modifyTime;
//    }
//
//    public void setModifyTime(Date modifyTime) {
//        this.modifyTime = modifyTime;
//    }
//
//}
