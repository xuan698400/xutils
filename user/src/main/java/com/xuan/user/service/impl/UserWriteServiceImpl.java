package com.xuan.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.common.enums.BwResultCodeEnum;
import com.xuan.common.exception.BwException;
import com.xuan.common.utils.CheckUtil;
import com.xuan.user.dao.UserDao;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.entity.User;
import com.xuan.user.model.query.UserQuery;
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
        checkCreate(userDO);

        User user = UserConvert.toEntity(userDO);
        return userDao.insert(user);
    }

    private void checkCreate(UserDO userDO) {
        if (CheckUtil.isEmpty(userDO.getBizCode())) {
            throw new BwException(BwResultCodeEnum.PARAM_INVALID.getCode(), "业务身份不能为空");
        }
        if (CheckUtil.isEmpty(userDO.getUsername())) {
            throw new BwException(BwResultCodeEnum.PARAM_INVALID.getCode(), "用户名不能空");
        }
        if (CheckUtil.isEmpty(userDO.getPassword())) {
            throw new BwException(BwResultCodeEnum.PARAM_INVALID.getCode(), "密码不能为空");
        }
        if (CheckUtil.isNull(userDO.getStatus())) {
            throw new BwException(BwResultCodeEnum.PARAM_INVALID.getCode(), "状态不能空");
        }
        if (CheckUtil.isNull(userDO.getType())) {
            throw new BwException(BwResultCodeEnum.PARAM_INVALID.getCode(), "类型不能空");
        }

        //用户名不能重复
        UserQuery query = new UserQuery();
        query.setBizCode(userDO.getBizCode());
        query.setUsername(userDO.getUsername());
        List<User> userList = userDao.selectByQuery(query);
        if (CheckUtil.isEmpty(userList)) {
            throw new BwException(BwResultCodeEnum.PARAM_INVALID.getCode(), "用户名不能重复");
        }
    }

}
