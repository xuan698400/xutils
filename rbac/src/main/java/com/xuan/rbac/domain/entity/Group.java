package com.xuan.rbac.domain.entity;

import java.util.List;

/**
 * 组织架构中组的概念
 *
 * @author xuan
 * @since 2021/9/2
 */
public class Group {
    /**
     * 组唯一标志
     */
    private Long groupId;
    /**
     * 组名称
     */
    private String name;
    /**
     * 组的下属组
     */
    private List<Group> subGroups;
}
