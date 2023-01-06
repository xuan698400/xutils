package com.xuan.moho.sql.model;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class Column {

    /**
     * 列名
     */
    private String name;

    /**
     * 列数据类型
     */
    private ColumnType type;

    /**
     * 是否不能NULL
     */
    private Boolean isNotNull;

    /**
     * 是否是无符号（整数类型才有效果）
     */
    private Boolean isUnsigned;

    /**
     * 评论
     */
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public Boolean getNotNull() {
        return isNotNull;
    }

    public void setNotNull(Boolean notNull) {
        isNotNull = notNull;
    }

    public Boolean getUnsigned() {
        return isUnsigned;
    }

    public void setUnsigned(Boolean unsigned) {
        isUnsigned = unsigned;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
