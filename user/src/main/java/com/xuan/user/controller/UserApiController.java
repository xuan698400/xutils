package com.xuan.user.controller;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.common.model.BwResult;
import com.xuan.common.model.BwResultBuilder;
import com.xuan.user.enums.UserStatusEnum;
import com.xuan.user.enums.UserTypeEnum;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.query.UserQuery;
import com.xuan.user.model.vo.UserCreateVO;
import com.xuan.user.model.vo.UserVO;
import com.xuan.user.service.UserReadService;
import com.xuan.user.service.UserWriteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2020/3/14
 */
@RestController
public class UserApiController {

    @Resource
    private UserReadService userReadService;
    @Resource
    private UserWriteService userWriteService;

    public BwResult<Long> userAdd(UserCreateVO userCreateVO) {
        UserDO userDO = new UserDO();
        userDO.setBizCode(userCreateVO.getBizCode());
        userDO.setUsername(userCreateVO.getUsername());
        userDO.setPassword(userCreateVO.getPassword());
        userDO.setPhone(userCreateVO.getPhone());
        userDO.setEmail(userCreateVO.getEmail());
        userDO.setStatus(UserStatusEnum.NORMAL);
        userDO.setType(UserTypeEnum.NORMAL);
        Long newId = userWriteService.create(userDO);
        return BwResultBuilder.buildSuccess(newId);
    }

    @RequestMapping(value = "/user/userList")
    public BwResult<List<UserVO>> userList(UserQuery userQuery) {
        List<UserDO> userDOList = userReadService.queryUser(userQuery, null);
        List<UserVO> userVOList = UserConvert.toVOList(userDOList);
        return BwResultBuilder.buildSuccess(userVOList);
    }

}
