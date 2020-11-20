package com.xuan.user.service;

import java.util.List;

import com.xuan.user.common.UserPageQuery;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.request.UserQueryRequest;

/**
 * @author xuan
 * @since 2020/3/14
 */
public interface UserReadService {

    List<UserDO> queryUser(UserQueryRequest userQuery, UserPageQuery userPageQuery);

}
