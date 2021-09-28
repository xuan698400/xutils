package com.xuan.mix.domain.product.model;

import java.util.List;

import com.xuan.mix.domain.cpv.model.AttributePair;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class ProductSku {

    private Long id;

    private Long productId;

    private List<AttributePair> attributePairList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<AttributePair> getAttributePairList() {
        return attributePairList;
    }

    public void setAttributePairList(List<AttributePair> attributePairList) {
        this.attributePairList = attributePairList;
    }

}
