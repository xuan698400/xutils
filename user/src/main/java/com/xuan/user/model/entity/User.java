package com.xuan.user.model.entity;

import java.util.Date;

import com.xuan.common.model.BwBaseDO;
import lombok.Data;

/**
 * @author xuan
 * @since 2019/11/17
 */
@Data
public class User extends BwBaseDO {
    private static final long serialVersionUID = -1L;

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private String icon;

    private String password;

    private Integer type;

    private Integer status;

    private String feature;

    private String bizCode;

    private Date createTime;

    private Date modifyTime;

}
