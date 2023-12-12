package com.xuan.xutils.sql.ddl.spec;

import java.util.List;

import com.xuan.xutils.sql.common.Assert;
import com.xuan.xutils.sql.common.CollectionUtils;
import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.common.StringUtils;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class TableSpec {
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

    public String createTableSQL(SQLSyntax sqlSyntax) {
        Assert.notEmpty(name, "表名[name]不能为空");
        Assert.notEmpty(columnList, "表列[columnList]不能为空");
        Assert.notNull(primaryKey, "表主键[primaryKey]不能为空");

        return CREATE_SQL_TEMPLATE
            .replace("#tableName#", name)
            .replace("#columns#", buildCreateSqlForColumns(sqlSyntax))
            .replace("#primaryKey#", buildCreateSqlForPrimaryKey(sqlSyntax))
            .replace("#uniqueKey#", buildCreateSqlForUniqueKey(sqlSyntax))
            .replace("#key#", buildCreateSqlForKey(sqlSyntax))
            .replace("#charset#", StringUtils.isNotEmpty(charset) ? "CHARSET=" + charset : "")
            .replace("#comment#", StringUtils.isNotEmpty(comment) ? "COMMENT='" + comment + "'" : "");
    }

    private String buildCreateSqlForKey(SQLSyntax sqlSyntax) {
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

    private String buildCreateSqlForUniqueKey(SQLSyntax sqlSyntax) {
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

    private String buildCreateSqlForPrimaryKey(SQLSyntax sqlSyntax) {
        String primaryKeySql = String.format("PRIMARY KEY (%s)", primaryKey.getName());
        if (CollectionUtils.isNotEmpty(uniqueKeysList) || CollectionUtils.isNotEmpty(keysList)) {
            primaryKeySql += ",";
        }
        return primaryKeySql;
    }

    private String buildCreateSqlForColumns(SQLSyntax sqlSyntax) {
        StringBuilder sb = new StringBuilder();
        columnList.forEach(columnSpec -> sb.append(columnSpec.buildCreateTableColumnSQL(sqlSyntax)).append(","));
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnSpec> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnSpec> columnList) {
        this.columnList = columnList;
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

    public ColumnSpec getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnSpec primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<List<ColumnSpec>> getUniqueKeysList() {
        return uniqueKeysList;
    }

    public void setUniqueKeysList(List<List<ColumnSpec>> uniqueKeysList) {
        this.uniqueKeysList = uniqueKeysList;
    }

    public List<List<ColumnSpec>> getKeysList() {
        return keysList;
    }

    public void setKeysList(List<List<ColumnSpec>> keysList) {
        this.keysList = keysList;
    }
}
