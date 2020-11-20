package com.xuan.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.user.common.UserCheckUtil;
import com.xuan.user.common.UserException;
import com.xuan.user.common.UserExceptionCodeEnum;
import com.xuan.user.dao.UserDao;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.entity.User;
import com.xuan.user.model.request.UserQueryRequest;
import com.xuan.user.service.UserWriteService;
import org.springframework.stereotype.Service;

/**
 * @author xuan
 * @since 2020/3/14
 */
@Service
public class UserWriteServiceImpl implements UserWriteService {

    @Resource
    private UserDao userDao;

    @Override
    public Long create(UserDO userDO) {
        try {
            checkCreate(userDO);
            User user = UserConvert.toEntity(userDO);
            return userDao.insert(user);
        } catch (UserException ue) {
            throw ue;
        } catch (Exception e) {
            throw new UserException(UserExceptionCodeEnum.UNKNOW_EXCEPTION, e.getMessage());
        }
    }

    private void checkCreate(UserDO userDO) {
        UserCheckUtil.checkEmpty(userDO.getBizCode(), "bizCode");
        UserCheckUtil.checkEmpty(userDO.getUsername(), "username");
        UserCheckUtil.checkEmpty(userDO.getPassword(), "password");
        UserCheckUtil.checkNull(userDO.getStatus(), "status");
        UserCheckUtil.checkNull(userDO.getType(), "type");

        //用户名不能重复
        UserQueryRequest query = new UserQueryRequest();
        query.setBizCode(userDO.getBizCode());
        query.setUsername(userDO.getUsername());
        List<User> userList = userDao.selectByQuery(query);
        if (UserCheckUtil.isNotEmpty(userList)) {
            throw new UserException(UserExceptionCodeEnum.USERNAME_REPEAT, userDO.getUsername());
        }
    }

}
