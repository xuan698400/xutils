package com.xuan.user.model.request;

/**
 * @author xuan
 * @since 2020/2/28
 */
public class UserIdQueryRequest extends BaseRequest {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
