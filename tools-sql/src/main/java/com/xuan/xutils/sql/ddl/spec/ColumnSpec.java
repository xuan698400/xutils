package com.xuan.xutils.sql.ddl.spec;

import com.xuan.xutils.sql.common.Assert;
import com.xuan.xutils.sql.common.BooleanUtils;
import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.common.StringUtils;

/**
 * 数据库列元数据
 *
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnSpec {

    /**
     * 列名
     */
    private String name;

    /**
     * 列类型
     */
    private ColumnTypeSpec columnType;

    /**
     * 是否无符号数
     */
    private Boolean unsigned;

    /**
     * 是否不为空
     */
    private Boolean notNull;

    /**
     * 是否自增
     */
    private Boolean autoIncrement;

    /**
     * 描述
     */
    private String comment;

    public String buildCreateTableColumnSQL(SQLSyntax sqlSyntax) {
        Assert.notEmpty(name, "字段[name]不能为空");
        Assert.notNull(columnType, "字段类型[columnType]不能为空");

        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ")
            .append(columnType.buildCreateTableColumnTypeSQL(sqlSyntax)).append(" ")
            .append(BooleanUtils.isTure(unsigned) ? "unsigned" : "").append(" ")
            .append(BooleanUtils.isTure(notNull) ? "NOT NULL" : "DEFAULT NULL").append(" ")
            .append(BooleanUtils.isTure(autoIncrement) ? "AUTO_INCREMENT" : "").append(" ")
            .append(StringUtils.isNotEmpty(comment) ? String.format("COMMENT '%s'", comment) : "").append(" ");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnTypeSpec getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnTypeSpec columnType) {
        this.columnType = columnType;
    }

    public Boolean getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(Boolean unsigned) {
        this.unsigned = unsigned;
    }

    public Boolean getNotNull() {
        return notNull;
    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
    }

    public Boolean getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
