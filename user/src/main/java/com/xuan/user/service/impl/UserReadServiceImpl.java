package com.xuan.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.user.common.UserPageQuery;
import com.xuan.user.dao.UserDao;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.entity.User;
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
    public List<UserDO> queryUser(UserQueryRequest userQueryRequest, UserPageQuery userPageQuery) {
        List<User> userList = userDao.selectByQuery(userQueryRequest);
        return UserConvert.toDOList(userList);
    }

}
