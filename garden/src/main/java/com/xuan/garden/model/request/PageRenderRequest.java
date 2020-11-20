package com.xuan.garden.model.request;

/**
 * @author xuan
 * @since 2020/11/19
 */
public class PageRenderRequest extends BaseRequest {

    private Long pageId;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

}
