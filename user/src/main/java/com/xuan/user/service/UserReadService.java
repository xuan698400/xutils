package com.xuan.user.service;

import java.util.List;

import com.xuan.user.model.domain.User;
import com.xuan.user.model.request.UserIdQueryRequest;
import com.xuan.user.model.request.UserQueryRequest;

/**
 * @author xuan
 * @since 2020/3/14
 */
public interface UserReadService {

    List<User> queryUser(UserQueryRequest userQuery);

    User getUserById(UserIdQueryRequest request);

}
