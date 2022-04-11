package com.xuan.mix.biz.category.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/9/27
 */
public class Category {
    /**
     * 类目ID
     */
    private Long id;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类目描述
     */
    private String desc;
    /**
     * 类目创建时间
     */
    private Date createTime;
    /**
     * 类目修改时间
     */
    private Date modifyTime;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 数据版本
     */
    private Long version;
    /**
     * 类目状态
     */
    private Integer status;
    /**
     * 孩子类目
     */
    private List<Category> children;
    /**
     * 父级类目
     */
    private Category parent;
    /**
     * 扩展属性
     */
    private Map<String, String> feature;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Map<String, String> getFeature() {
        return feature;
    }

    public void setFeature(Map<String, String> feature) {
        this.feature = feature;
    }

}
