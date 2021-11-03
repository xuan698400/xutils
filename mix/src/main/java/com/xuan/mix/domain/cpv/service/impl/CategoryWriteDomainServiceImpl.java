package com.xuan.mix.domain.cpv.service.impl;

import javax.annotation.Resource;

import com.xuan.mix.common.exception.Assert;
import com.xuan.mix.domain.cpv.model.Category;
import com.xuan.mix.domain.cpv.service.CategoryWriteDomainService;
import com.xuan.mix.domain.middleware.repository.CategoryRepository;
import com.xuan.mix.domain.middleware.sequence.Sequence;
import com.xuan.mix.domain.middleware.sequence.SequenceType;
import com.xuan.mix.domain.share.model.OperationOption;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/9/27
 */
@Component
public class CategoryWriteDomainServiceImpl implements CategoryWriteDomainService {

    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private Sequence sequence;

    @Override
    public Long add(Category category, OperationOption option) {

        Assert.notNull(category, "category is null.");
        Assert.notEmpty(category.getName(), "category.name is empty.");

        Long newId = sequence.createId(SequenceType.CATEGORY);
        category.setId(newId);

        return categoryRepository.add(category, option);
    }

    @Override
    public void rename(Category category, OperationOption option) {

        Assert.notNull(category, "category is null.");
        Assert.notEmpty(category.getName(), "category.name is empty.");

        categoryRepository.update(category, option);
    }

    @Override
    public void delete(Category category, OperationOption option) {
        Assert.notNull(category, "category is null.");
        Assert.notNull(category.getId(), "category.id is null.");

        categoryRepository.delete(category, option);
    }

}
