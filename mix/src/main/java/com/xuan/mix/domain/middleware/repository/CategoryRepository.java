package com.xuan.mix.domain.middleware.repository;

import com.xuan.mix.domain.cpv.model.Category;
import com.xuan.mix.domain.share.model.OperationOption;

/**
 * @author xuan
 * @since 2021/9/28
 */
public interface CategoryRepository {

    Long add(Category category, OperationOption option);

    void update(Category category, OperationOption option);

    void delete(Category category, OperationOption option);
}
