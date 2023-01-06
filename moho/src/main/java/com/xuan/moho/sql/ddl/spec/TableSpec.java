package com.xuan.moho.sql.ddl.spec;

import java.util.List;

import com.xuan.moho.base.exception.Assert;
import com.xuan.moho.base.exception.BizExceptionCodeEnum;
import com.xuan.moho.base.utils.CollectionUtils;
import com.xuan.moho.base.utils.StringUtils;
import com.xuan.moho.sql.common.SqlSyntax;
import com.xuan.moho.sql.ddl.DdlBuilder;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class TableSpec implements DdlBuilder {
    private final static String CREATE_SQL_TEMPLATE
        = "CREATE TABLE #tableName# (#columns# #primaryKey# #uniqueKey# #key#) ENGINE=InnoDB #charset# #comment#;";

    /**
     * 表名
     */
    private String name;
    /**
     * 列信息
     */
    private List<ColumnSpec> columnList;
    /**
     * 表描述
     */
    private String comment;
    /**
     * 编码
     */
    private String charset;
    /**
     * 主键
     */
    private ColumnSpec primaryKey;
    /**
     * 联合唯一键，支持多个
     */
    private List<List<ColumnSpec>> uniqueKeysList;
    /**
     * 普通索引
     */
    private List<List<ColumnSpec>> keysList;

    @Override
    public String buildCreateSql(SqlSyntax sqlSyntax) {
        Assert.notEmpty(name, BizExceptionCodeEnum.PARAM_EMPTY.getCode(), "name is empty.");
        Assert.notEmpty(columnList, BizExceptionCodeEnum.PARAM_EMPTY.getCode(), "columnList is empty.");
        Assert.notNull(primaryKey, BizExceptionCodeEnum.PARAM_EMPTY.getCode(), "primaryKey is empty.");

        return CREATE_SQL_TEMPLATE
            .replace("#tableName#", name)
            .replace("#columns#", buildCreateSqlForColumns(sqlSyntax))
            .replace("#primaryKey#", buildCreateSqlForPrimaryKey(sqlSyntax))
            .replace("#uniqueKey#", buildCreateSqlForUniqueKey(sqlSyntax))
            .replace("#key#", buildCreateSqlForKey(sqlSyntax))
            .replace("#charset#", StringUtils.isNotEmpty(charset) ? "CHARSET=" + charset : "")
            .replace("#comment#", StringUtils.isNotEmpty(comment) ? "COMMENT='" + comment + "'" : "");
    }

    private String buildCreateSqlForKey(SqlSyntax sqlSyntax) {
        if (CollectionUtils.isEmpty(keysList)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        keysList.forEach(keys -> {
            String join1 = joinColumnName(keys, "_");
            String join2 = joinColumnName(keys, ",");
            sb.append(String.format("KEY %s (%s)", join1, join2));
            sb.append(",");
        });
        return sb.substring(0, sb.length() - 1);
    }

    private String buildCreateSqlForUniqueKey(SqlSyntax sqlSyntax) {
        if (CollectionUtils.isEmpty(uniqueKeysList)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        uniqueKeysList.forEach(uniqueKeys -> {
            String join1 = joinColumnName(uniqueKeys, "_");
            String join2 = joinColumnName(uniqueKeys, ",");
            sb.append(String.format("UNIQUE KEY %s (%s)", join1, join2));
            sb.append(",");
        });
        if (CollectionUtils.isEmpty(keysList)) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    private String buildCreateSqlForPrimaryKey(SqlSyntax sqlSyntax) {
        String primaryKeySql = String.format("PRIMARY KEY (%s)", primaryKey.getName());
        if (CollectionUtils.isNotEmpty(uniqueKeysList) || CollectionUtils.isNotEmpty(keysList)) {
            primaryKeySql += ",";
        }
        return primaryKeySql;
    }

    private String buildCreateSqlForColumns(SqlSyntax sqlSyntax) {
        StringBuilder sb = new StringBuilder();
        columnList.forEach(columnSpec -> sb.append(columnSpec.getCreateSql(sqlSyntax)).append(","));
        return sb.toString();
    }

    private String joinColumnName(List<ColumnSpec> columnList, String sp) {
        StringBuilder sb = new StringBuilder();
        columnList.forEach(columnSpec -> {
            if (sb.length() > 0) {
                sb.append(sp);
            }
            sb.append(columnSpec.getName());
        });
        return sb.toString();
    }

    public static class Builder {
        private String name;
        private List<ColumnSpec> columnList;
        private String comment;
        private String charset;
        private ColumnSpec primaryKey;
        private List<List<ColumnSpec>> uniqueKeysList;
        private List<List<ColumnSpec>> keysList;

        public Builder name(String name) {
            Assert.notEmpty(name, BizExceptionCodeEnum.PARAM_EMPTY.getCode(), "name is empty.");
            this.name = name;
            return this;
        }

        public Builder columnList(List<ColumnSpec> columnList) {
            Assert.notEmpty(columnList, BizExceptionCodeEnum.PARAM_NULL.getCode(), "columnList is empty.");
            this.columnList = columnList;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder charset(String charset) {
            this.charset = charset;
            return this;
        }

        public Builder primaryKey(ColumnSpec primaryKey) {
            this.primaryKey = primaryKey;
            return this;
        }

        public Builder uniqueKeysList(List<List<ColumnSpec>> uniqueKeysList) {
            this.uniqueKeysList = uniqueKeysList;
            return this;
        }

        public Builder keysList(List<List<ColumnSpec>> keysList) {
            this.keysList = keysList;
            return this;
        }

        public TableSpec build() {
            TableSpec tableSpec = new TableSpec();
            tableSpec.name = this.name;
            tableSpec.columnList = this.columnList;
            tableSpec.comment = this.comment;
            tableSpec.charset = this.charset;
            tableSpec.primaryKey = this.primaryKey;
            tableSpec.uniqueKeysList = this.uniqueKeysList;
            tableSpec.keysList = this.keysList;
            return tableSpec;
        }
    }

    public static Builder builder() {
        return new TableSpec.Builder();
    }

}
