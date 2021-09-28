package com.xuan.mix.domain.cpv.service.impl;

import javax.annotation.Resource;

import com.xuan.mix.domain.common.BaseDomainService;
import com.xuan.mix.domain.cpv.model.Category;
import com.xuan.mix.domain.cpv.service.CategoryWriteDomainService;
import com.xuan.mix.domain.cpv.service.action.CategoryAddCheckAction;
import com.xuan.mix.domain.cpv.service.action.CategorySaveAction;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/9/27
 */
@Component
public class CategoryWriteDomainServiceImpl extends BaseDomainService implements CategoryWriteDomainService {

    @Resource
    private CategoryAddCheckAction categoryAddCheckAction;
    @Resource
    private CategorySaveAction categorySaveAction;

    @Override
    public Long add(Category category) {

        //1、逻辑校验
        categoryAddCheckAction.check(category);

        //2、保存
        return categorySaveAction.save(category);
    }

}
