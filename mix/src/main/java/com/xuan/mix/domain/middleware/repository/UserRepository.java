package com.xuan.mix.domain.middleware.repository;

import com.xuan.mix.domain.share.model.OperationOption;
import com.xuan.mix.domain.user.model.User;

/**
 * @author xuan
 * @since 2021/10/4
 */
public interface UserRepository {

    void add(User user, OperationOption option);

    void update(User user, OperationOption option);
}
