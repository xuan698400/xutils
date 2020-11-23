package com.xuan.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.user.adapter.SeqAdapter;
import com.xuan.user.common.UserCheckUtil;
import com.xuan.user.common.UserException;
import com.xuan.user.common.UserExceptionCodeEnum;
import com.xuan.user.dao.UserDao;
import com.xuan.user.dao.model.UserDO;
import com.xuan.user.dao.model.UserQueryParams;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.User;
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
    @Resource
    private SeqAdapter seqAdapter;

    @Override
    public Long create(User user) {
        try {
            checkCreate(user);
            UserDO userDO = UserConvert.toDO(user);
            userDO.setId(seqAdapter.createNewUserId());
            return userDao.insert(userDO);
        } catch (UserException ue) {
            throw ue;
        } catch (Exception e) {
            throw new UserException(UserExceptionCodeEnum.UNKNOW_EXCEPTION, e.getMessage());
        }
    }

    private void checkCreate(User user) {
        UserCheckUtil.checkEmpty(user.getBizCode(), "bizCode");
        UserCheckUtil.checkEmpty(user.getUsername(), "username");
        UserCheckUtil.checkEmpty(user.getPassword(), "password");
        UserCheckUtil.checkNull(user.getStatus(), "status");
        UserCheckUtil.checkNull(user.getType(), "type");

        //用户名不能重复
        UserQueryParams params = new UserQueryParams();
        params.setBizCode(user.getBizCode());
        params.setUsername(user.getUsername());
        List<UserDO> userDOList = userDao.selectByQuery(params);
        if (UserCheckUtil.isNotEmpty(userDOList)) {
            throw new UserException(UserExceptionCodeEnum.USERNAME_REPEAT, user.getUsername());
        }
    }

}
