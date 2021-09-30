package com.xuan.mix.domain.shop.model;

import java.util.List;
import java.util.Set;

import com.xuan.mix.domain.share.model.Image;
import com.xuan.mix.domain.share.model.Video;
import com.xuan.mix.domain.shop.model.businesstime.BusinessTime;

/**
 * @author xuan
 * @since 2021/9/30
 */
public class Shop {

    private Long shopId;

    private String title;

    private String subTitle;

    private PoiInfo poiInfo;

    private BusinessTime businessTime;

    private List<Image> imageList;

    private List<Video> videoList;

    private Set<Integer> tagSet;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public PoiInfo getPoiInfo() {
        return poiInfo;
    }

    public void setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }

    public BusinessTime getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(BusinessTime businessTime) {
        this.businessTime = businessTime;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public Set<Integer> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Integer> tagSet) {
        this.tagSet = tagSet;
    }

}
