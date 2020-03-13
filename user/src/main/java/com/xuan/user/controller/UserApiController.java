package com.xuan.user.controller;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.common.model.BwResult;
import com.xuan.common.model.BwResultBuilder;
import com.xuan.user.model.convert.UserConvert;
import com.xuan.user.model.domain.UserDO;
import com.xuan.user.model.query.UserQuery;
import com.xuan.user.model.vo.UserVO;
import com.xuan.user.service.UserReadService;
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

    @RequestMapping(value = "/user/userList")
    public BwResult<List<UserVO>> userList(UserQuery userQuery) {

        List<UserDO> userDOList = userReadService.queryUser(userQuery, null);
        List<UserVO> userVOList = UserConvert.toVOList(userDOList);
        return BwResultBuilder.buildSuccess(userVOList);
    }

}
