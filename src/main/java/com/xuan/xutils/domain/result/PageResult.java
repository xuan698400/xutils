package com.xuan.xutils.domain.result;

/**
 * 含有分页结果的返回结果对象
 * <p>
 * Created by xuan on 17/8/2.
 */
public class PageResult extends Result {
    private static final long serialVersionUID = 1L;

    /**
     * 分页对象
     */
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * 返回分页数据
     *
     * @param result
     * @param page
     * @return
     */
    public static PageResult obtainPage(Object result, Page page) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(CodeEnum.SUCCESS.getCode());
        pageResult.setMessage("成功");
        pageResult.setResult(result);
        pageResult.setPage(page);
        return pageResult;
    }
}
