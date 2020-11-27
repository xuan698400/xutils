package com.xuan.rbac.dao.model;

/**
 * @author xuan
 * @since 2020/11/27
 */
public class PermissionDO extends BaseDO {

    private String name;

    @Override
    public String toString() {
        return super.toString() + ", PermissionDO{" +
            "name='" + name + '\'' +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
