package com.xuan.mix.domain.cpv.service;

import com.xuan.mix.domain.cpv.model.Category;

/**
 * @author xuan
 * @since 2021/9/27
 */
public interface CategoryWriteDomainService {

    Long add(Category category);

    void rename(Category category);
}
