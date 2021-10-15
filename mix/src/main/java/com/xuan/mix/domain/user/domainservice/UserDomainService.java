package com.xuan.mix.domain.user.domainservice;

import com.xuan.mix.domain.share.model.OperationOption;
import com.xuan.mix.domain.user.model.User;

/**
 * @author xuan
 * @since 2021/10/15
 */
public interface UserDomainService {
    
    Long create(User user, OperationOption option);
}
