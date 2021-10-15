package com.xuan.mix.domain.user.domainservice.impl;

import javax.annotation.Resource;

import com.xuan.mix.common.exception.Assert;
import com.xuan.mix.domain.middleware.sequence.Sequence;
import com.xuan.mix.domain.middleware.sequence.SequenceType;
import com.xuan.mix.domain.share.model.OperationOption;
import com.xuan.mix.domain.user.domainservice.UserDomainService;
import com.xuan.mix.domain.user.model.User;
import com.xuan.mix.domain.middleware.repository.UserRepository;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/10/15
 */
@Component
public class UserDomainServiceImpl implements UserDomainService {

    @Resource
    private Sequence sequence;

    @Resource
    private UserRepository userRepository;

    @Override
    public Long create(User user, OperationOption option) {

        Assert.notNull(user, "user is null.");
        Assert.notNull(user.getUsername(), "user.username is null.");
        Assert.notNull(user.getStatusEnum(), "user.statusEnum is null.");

        Long newUserId = sequence.createId(SequenceType.USER);
        user.setId(newUserId);

        userRepository.add(user, option);
        return newUserId;
    }

}
