package com.xuan.mix.domain.cpv.service.impl;

import javax.annotation.Resource;

import com.xuan.mix.domain.common.BaseDomainService;
import com.xuan.mix.domain.cpv.model.Category;
import com.xuan.mix.domain.cpv.service.CategoryWriteDomainService;
import com.xuan.mix.domain.cpv.service.action.CategoryCheckAction;
import com.xuan.mix.domain.cpv.service.action.CategoryPersistAction;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/9/27
 */
@Component
public class CategoryWriteDomainServiceImpl extends BaseDomainService implements CategoryWriteDomainService {

    @Resource
    private CategoryCheckAction categoryCheckAction;
    @Resource
    private CategoryPersistAction categoryPersistAction;

    @Override
    public Long add(Category category) {

        categoryCheckAction.check(category);

        return categoryPersistAction.add(category);
    }

    @Override
    public void rename(Category category) {
        categoryCheckAction.check(category);

        categoryPersistAction.update(category);
    }

}
