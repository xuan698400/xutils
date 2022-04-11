package com.xuan.mix.biz.category.dao;

import com.xuan.mix.biz.category.model.CategoryAdd;

/**
 * @author xuan
 * @since 2022/3/30
 */
public interface CategoryDao {
    
    Long addCategory(CategoryAdd categoryAdd);
}
