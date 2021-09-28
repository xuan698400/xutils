package com.xuan.mix.domain.product.model;

import java.util.List;

import com.xuan.mix.domain.cpv.model.AttributePair;
import com.xuan.mix.domain.cpv.model.Category;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class Spu {

    private Long id;

    private String name;

    private List<AttributePair> attributePairList;

    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttributePair> getAttributePairList() {
        return attributePairList;
    }

    public void setAttributePairList(List<AttributePair> attributePairList) {
        this.attributePairList = attributePairList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
}
