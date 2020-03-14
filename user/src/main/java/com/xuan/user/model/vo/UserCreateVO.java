package com.xuan.user.model.vo;

import com.xuan.common.model.BwRequest;
import lombok.Data;

/**
 * @author xuan
 * @since 2020/3/14
 */
@Data
public class UserCreateVO extends BwRequest {

    private String username;

    private String name;

    private String email;

    private String phone;

    private String password;
}
