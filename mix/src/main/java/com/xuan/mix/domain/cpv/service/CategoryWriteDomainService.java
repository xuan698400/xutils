package com.xuan.mix.domain.cpv.service;

import com.xuan.mix.domain.cpv.model.Category;
import com.xuan.mix.domain.share.model.OperationOption;

/**
 * @author xuan
 * @since 2021/9/27
 */
public interface CategoryWriteDomainService {

    Long add(Category category, OperationOption option);

    void rename(Category category, OperationOption option);

    void delete(Category category, OperationOption option);
}
