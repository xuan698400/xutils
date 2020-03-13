package com.xuan.user.dao;

import java.util.List;

import com.xuan.user.model.entity.User;
import com.xuan.user.model.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xuan
 * @since 2019/11/17
 */
@Mapper
public interface UserDao {

    Long insert(User user);

    int updateFeatureById(@Param("id") Long userId, @Param("feature") String feature);

    User selectById(@Param("id") Long id);

    List<User> selectByQuery(UserQuery query);

}
