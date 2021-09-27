package com.xuan.mix.domain.cpv.model;

import java.util.List;

/**
 * @author xuan
 * @since 2021/9/27
 */
public class Category {

    private Long id;

    private String name;

    private List<Category> children;

    private Category parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

}
