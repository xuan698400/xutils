package com.xuan.mix.domain.cpv.service.action;

import javax.annotation.Resource;

import com.xuan.mix.domain.cpv.model.Category;
import com.xuan.mix.domain.repository.CategoryRepository;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/9/28
 */
@Component
public class CategorySaveAction {

    @Resource
    private CategoryRepository categoryRepository;

    public Long save(Category category) {
        return categoryRepository.add(category);
    }

}
