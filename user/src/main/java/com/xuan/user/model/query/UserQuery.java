package com.xuan.user.model.query;

import java.util.List;

import com.xuan.common.model.BwBaseDO;
import lombok.Data;

/**
 * @author xuan
 * @since 2020/2/28
 */
@Data
public class UserQuery extends BwBaseDO {
    private static final long serialVersionUID = -1L;

    private String bizCode;

    private String email;

    private String username;

    private List<Integer> statusList;
}
