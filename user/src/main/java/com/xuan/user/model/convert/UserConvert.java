package com.xuan.user.model.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import com.xuan.user.enums.UserStatusEnum;
import com.xuan.user.enums.UserTypeEnum;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.entity.User;
import com.xuan.user.model.vo.UserVO;

/**
 * @author xuan
 * @since 2020/1/10
 */
public class UserConvert {

    public static User toEntity(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDO.getUsername());
        user.setName(userDO.getName());
        user.setPhone(userDO.getPhone());
        user.setEmail(userDO.getEmail());
        user.setIcon(userDO.getIcon());
        user.setType(null == userDO.getType() ? null : userDO.getType().getValue());
        user.setStatus(null == userDO.getStatus() ? null : userDO.getStatus().getValue());
        user.setFeature(null == userDO.getFeatureMap() ? null : JSON.toJSONString(userDO.getFeatureMap()));
        user.setBizCode(userDO.getBizCode());
        user.setCreateTime(userDO.getCreateTime());
        user.setModifyTime(userDO.getModifyTime());
        return user;
    }

    public static UserDO toDO(User user) {
        if (user == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        userDO.setId(user.getId());
        userDO.setUsername(user.getUsername());
        userDO.setName(user.getName());
        userDO.setPhone(user.getPhone());
        userDO.setEmail(user.getEmail());
        userDO.setIcon(user.getIcon());
        userDO.setPassword(user.getPassword());
        userDO.setType(UserTypeEnum.valueOf(user.getType()));
        userDO.setStatus(UserStatusEnum.valueOf(user.getStatus()));
        userDO.setFeatureMap(JSON.parseObject(user.getFeature()));
        userDO.setBizCode(user.getBizCode());
        userDO.setCreateTime(user.getCreateTime());
        userDO.setModifyTime(user.getModifyTime());
        return userDO;
    }

    public static List<UserDO> toDOList(List<User> userList) {
        if (null == userList) {
            return new ArrayList<>();
        }
        return userList.stream().map(UserConvert::toDO).collect(Collectors.toList());
    }

    public static UserVO toVO(UserDO userDO) {
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

    public static List<UserVO> toVOList(List<UserDO> userDOList) {
        if (null == userDOList) {
            return new ArrayList<>();
        }
        return userDOList.stream().map(UserConvert::toVO).collect(Collectors.toList());
    }

}
