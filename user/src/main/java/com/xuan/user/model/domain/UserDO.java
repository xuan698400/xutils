package com.xuan.user.model.domain;

import java.util.Date;
import java.util.Map;

import com.xuan.common.model.BwBaseDO;
import com.xuan.user.enums.UserStatusEnum;
import com.xuan.user.enums.UserTypeEnum;
import lombok.Data;

/**
 * @author xuan
 * @since 2020/3/14
 */
@Data
public class UserDO extends BwBaseDO {
    private static final long serialVersionUID = -1L;

    private Long id;

    private String name;

    private UserTypeEnum type;

    private UserStatusEnum status;

    private String username;

    private String phone;

    private String email;

    private String icon;

    private Map<String, Object> featureMap;

    private Date createTime;

    private Date modifyTime;

    private String token;

    private String bizCode;

    private String password;
}
