package com.xuan.rbac.dao;

import com.xuan.rbac.dao.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xuan
 * @since 2020/11/27
 */
@Mapper
public interface UserDao {

    Long insert(UserDO user);
}
