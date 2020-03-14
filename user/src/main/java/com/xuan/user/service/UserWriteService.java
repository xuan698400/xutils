package com.xuan.user.service;

import com.xuan.user.model.domain.UserDO;

/**
 * @author xuan
 * @since 2020/3/14
 */
public interface UserWriteService {

    Long create(UserDO userDO);
}
