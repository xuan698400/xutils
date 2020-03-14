package com.xuan.user.service;

import java.util.List;

import com.xuan.common.model.BwPageQuery;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.query.UserQuery;

/**
 * @author xuan
 * @since 2020/3/14
 */
public interface UserReadService {

    List<UserDO> queryUser(UserQuery userQuery, BwPageQuery pageQuery);

}
