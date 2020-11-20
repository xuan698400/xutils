package com.xuan.user.controller;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.user.common.UserException;
import com.xuan.user.common.UserStatusEnum;
import com.xuan.user.common.UserTypeEnum;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.request.UserQueryRequest;
import com.xuan.user.model.request.UserCreateRequest;
import com.xuan.user.model.response.UserResponse;
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
@RequestMapping(value = "/user/")
public class UserApiController {

    @Resource
    private UserReadService userReadService;
    @Resource
    private UserWriteService userWriteService;

    @RequestMapping(value = "userAdd")
    public UserResponse<Long> userAdd(UserCreateRequest userCreateRequest) {
        try {
            UserDO addUserDO = new UserDO();
            addUserDO.setBizCode(userCreateRequest.getBizCode());
            addUserDO.setUsername(userCreateRequest.getUsername());
            addUserDO.setPassword(userCreateRequest.getPassword());
            addUserDO.setPhone(userCreateRequest.getPhone());
            addUserDO.setEmail(userCreateRequest.getEmail());
            addUserDO.setStatus(UserStatusEnum.NORMAL);
            addUserDO.setType(UserTypeEnum.NORMAL);
            Long newId = userWriteService.create(addUserDO);
            return UserResponse.buildSuccess(newId);
        } catch (UserException ue) {
            return UserResponse.buildError(ue.getCode(), ue.getMsg());
        }
    }

    @RequestMapping(value = "userList")
    public UserResponse<List<UserVO>> userList(UserQueryRequest userQueryRequest) {
        List<UserDO> userDOList = userReadService.queryUser(userQueryRequest, null);
        List<UserVO> userVOList = UserConvert.toVOList(userDOList);
        return UserResponse.buildSuccess(userVOList);
    }

}
