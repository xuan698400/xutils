package com.xuan.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.user.dao.model.UserDO;
import com.xuan.user.dao.model.UserQueryParams;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.User;
import com.xuan.user.dao.UserDao;
import com.xuan.user.model.request.UserIdQueryRequest;
import com.xuan.user.model.request.UserQueryRequest;
import com.xuan.user.service.UserReadService;
import org.springframework.stereotype.Service;

/**
 * @author xuan
 * @since 2020/3/14
 */
@Service
public class UserReadServiceImpl implements UserReadService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> queryUser(UserQueryRequest userQueryRequest) {
        UserQueryParams params = new UserQueryParams();
        params.setBizCode(userQueryRequest.getBizCode());
        params.setEmail(userQueryRequest.getEmail());
        params.setUsername(userQueryRequest.getUsername());
        params.setStatusList(userQueryRequest.getStatusList());

        List<UserDO> userDOList = userDao.selectByQuery(params);
        return UserConvert.toUserList(userDOList);
    }

    @Override
    public User getUserById(UserIdQueryRequest userIdQueryRequest) {
        UserDO userDO = userDao.selectById(userIdQueryRequest.getUserId());
        return UserConvert.toUser(userDO);
    }

}
