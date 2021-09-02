package com.xuan.rbac.domain.entity;

import java.util.List;

/**
 * @author xuan
 * @since 2021/9/2
 */
public class User {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户所拥有的权限
     */
    private List<Permission> permissions;
}
