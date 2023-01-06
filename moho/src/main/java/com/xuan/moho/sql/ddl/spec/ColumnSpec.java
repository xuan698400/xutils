package com.xuan.moho.sql.ddl.spec;

import com.xuan.moho.base.exception.Assert;
import com.xuan.moho.base.exception.BizExceptionCodeEnum;
import com.xuan.moho.base.utils.BooleanUtils;
import com.xuan.moho.base.utils.StringUtils;
import com.xuan.moho.sql.common.SQLSyntax;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class ColumnSpec {

    private String name;
    private ColumnTypeSpec columnType;
    private Boolean unsigned;
    private Boolean notNull;
    private Boolean autoIncrement;
    private String comment;

    public String buildCreateTableColumnSQL(SQLSyntax sqlSyntax) {
        Assert.notEmpty(name, BizExceptionCodeEnum.PARAM_EMPTY.getCode(), "name is empty.");
        Assert.notNull(columnType, BizExceptionCodeEnum.PARAM_NULL.getCode(), "name is null.");

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

    public static class Builder {
        private String name;
        private ColumnTypeSpec columnType;
        private Boolean unsigned;
        private Boolean notNull;
        private Boolean autoIncrement;
        private String comment;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder columnType(ColumnTypeSpec columnType) {
            this.columnType = columnType;
            return this;
        }

        public Builder unsigned(Boolean unsigned) {
            this.unsigned = unsigned;
            return this;
        }

        public Builder notNull(Boolean notNull) {
            this.notNull = notNull;
            return this;
        }

        public Builder autoIncrement(Boolean autoIncrement) {
            this.autoIncrement = autoIncrement;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public ColumnSpec build() {
            ColumnSpec columnSpec = new ColumnSpec();
            columnSpec.name = this.name;
            columnSpec.columnType = this.columnType;
            columnSpec.unsigned = this.unsigned;
            columnSpec.notNull = this.notNull;
            columnSpec.autoIncrement = this.autoIncrement;
            columnSpec.comment = this.comment;
            return columnSpec;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
