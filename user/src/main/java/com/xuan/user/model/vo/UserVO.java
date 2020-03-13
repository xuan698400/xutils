package com.xuan.user.model.vo;

import com.xuan.common.model.BwBaseDO;
import lombok.Data;

/**
 * @author xuan
 * @since 2020/3/14
 */
@Data
public class UserVO extends BwBaseDO {
    private Long id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private String icon;

    private String token;
}
