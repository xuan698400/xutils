package com.xuan.mix.domain.cpv.repository;

import com.xuan.mix.domain.cpv.model.Category;

/**
 * @author xuan
 * @since 2021/9/28
 */
public interface CategoryRepository {

    Long add(Category category);

    void update(Category category);

    void delete(Category category);
}
