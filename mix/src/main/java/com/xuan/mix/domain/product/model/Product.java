package com.xuan.mix.domain.product.model;

import java.util.List;
import java.util.Set;

import com.xuan.mix.domain.cpv.model.Category;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class Product {

    private Long id;

    private String title;

    private String subTitle;

    private Spu spu;

    private Category category;

    private List<ProductImage> imageList;

    private List<ProductVideo> videoList;

    private ProductStatus status;

    private List<ProductSku> skuList;

    private String desc;

    private Set<Integer> tagSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<ProductImage> imageList) {
        this.imageList = imageList;
    }

    public List<ProductVideo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<ProductVideo> videoList) {
        this.videoList = videoList;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<ProductSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<ProductSku> skuList) {
        this.skuList = skuList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Integer> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Integer> tagSet) {
        this.tagSet = tagSet;
    }

}
