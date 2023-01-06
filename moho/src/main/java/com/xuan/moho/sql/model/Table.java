package com.xuan.moho.sql.model;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class Table {

    /**
     * 表名
     */
    private String name;
    /**
     * 表描述
     */
    private String comment;
    /**
     * 编码
     */
    private String charset;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

}
