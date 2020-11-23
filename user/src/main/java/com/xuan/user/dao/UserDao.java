package com.xuan.user.dao;

import java.util.List;

import com.xuan.user.dao.model.UserDO;
import com.xuan.user.dao.model.UserQueryParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xuan
 * @since 2019/11/17
 */
@Mapper
public interface UserDao {

    Long insert(UserDO user);

    int updateFeatureById(@Param("id") Long id, @Param("feature") String feature);

    UserDO selectById(@Param("id") Long id);

    List<UserDO> selectByQuery(UserQueryParams query);
}
