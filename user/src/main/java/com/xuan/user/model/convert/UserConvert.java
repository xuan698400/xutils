package com.xuan.user.model.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import com.xuan.user.common.UserStatusEnum;
import com.xuan.user.common.UserTypeEnum;
import com.xuan.user.dao.model.UserDO;
import com.xuan.user.model.domain.User;
import com.xuan.user.model.vo.UserVO;

/**
 * @author xuan
 * @since 2020/1/10
 */
public class UserConvert {

    public static UserDO toDO(User user) {
        if (user == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        userDO.setUsername(user.getUsername());
        userDO.setName(user.getName());
        userDO.setPhone(user.getPhone());
        userDO.setEmail(user.getEmail());
        userDO.setIcon(user.getIcon());
        userDO.setType(null == user.getType() ? null : user.getType().getValue());
        userDO.setStatus(null == user.getStatus() ? null : user.getStatus().getValue());
        userDO.setFeature(null == user.getFeatureMap() ? null : JSON.toJSONString(user.getFeatureMap()));
        userDO.setBizCode(user.getBizCode());
        userDO.setCreateTime(user.getCreateTime());
        userDO.setModifyTime(user.getModifyTime());
        userDO.setPassword(user.getPassword());
        return userDO;
    }

    public static User toUser(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDO.getId());
        user.setUsername(userDO.getUsername());
        user.setName(userDO.getName());
        user.setPhone(userDO.getPhone());
        user.setEmail(userDO.getEmail());
        user.setIcon(userDO.getIcon());
        user.setPassword(userDO.getPassword());
        user.setType(UserTypeEnum.valueOf(userDO.getType()));
        user.setStatus(UserStatusEnum.valueOf(userDO.getStatus()));
        user.setFeatureMap(JSON.parseObject(userDO.getFeature()));
        user.setBizCode(userDO.getBizCode());
        user.setCreateTime(userDO.getCreateTime());
        user.setModifyTime(userDO.getModifyTime());
        return user;
    }

    public static List<User> toUserList(List<UserDO> userDOList) {
        if (null == userDOList) {
            return new ArrayList<>();
        }
        return userDOList.stream().map(UserConvert::toUser).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static UserVO toVO(User userDO) {
        if (userDO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setName(userDO.getName());
        userVO.setUsername(userDO.getUsername());
        userVO.setPhone(userDO.getPhone());
        userVO.setEmail(userDO.getEmail());
        userVO.setIcon(userDO.getIcon());
        userVO.setToken(userDO.getToken());
        return userVO;
    }

    public static List<UserVO> toVOList(List<User> userDOList) {
        if (null == userDOList) {
            return new ArrayList<>();
        }
        return userDOList.stream().map(UserConvert::toVO).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
