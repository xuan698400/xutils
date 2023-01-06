package com.xuan.mix.an.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.xuan.mix.an.common.model.page.PageOrderBy;
import com.xuan.mix.an.common.model.page.PageQuery;
import com.xuan.mix.an.common.utils.CollectionUtils;

/**
 * SQL生成工具类
 *
 * @author xuan
 * @since 2021/11/5
 */
public class SqlCreator {

    private final StringBuilder sql;
    private final List<Object> args;
    private boolean hasOrderBy = false;
    private boolean hasWhere;
    private boolean isFirst = true;

    public static SqlCreator selectTable(String tableName) {
        return new SqlCreator("SELECT * FROM " + tableName, false);
    }

    public static SqlCreator countTable(String tableName) {
        return new SqlCreator("SELECT count(*) FROM " + tableName, false);
    }

    public static SqlCreator deleteTable(String tableName) {
        return new SqlCreator("DELETE FROM " + tableName, false);
    }

    public SqlCreator(String baseSql) {
        this(baseSql, true);
    }

    public SqlCreator(String baseSql, boolean hasWhere) {
        if (null == baseSql || baseSql.trim().length() == 0) {
            throw new IllegalArgumentException("baseSql can't be null");
        }
        args = new ArrayList<>();
        sql = new StringBuilder();
        sql.append(baseSql.trim());
        this.hasWhere = hasWhere;
    }

    /**
     * 增加查询条件
     *
     * @param operator     操作，比如：AND、OR
     * @param expression   表达式，比如：id=1
     * @param precondition 先决条件，当为true时才会增加查询条件，比如 user != null
     */
    public SqlCreator addExpression(String operator, String expression, boolean precondition) {
        return addExpression(operator, expression, null, precondition);
    }

    /**
     * 增加查询条件
     *
     * @param operator     操作，比如：AND、OR
     * @param expression   表达式，比如：id=?
     * @param arg          表达式中的参数的值
     * @param precondition 先决条件，当为true时才会增加查询条件，比如 id != null
     */
    public SqlCreator addExpression(String operator, String expression, Object arg, boolean precondition) {
        if (precondition) {
            if (isFirst) {
                if (hasWhere) {
                    if (!sql.toString().toLowerCase().endsWith("where")) {
                        sql.append(" " + operator);
                    }
                } else {
                    sql.append(" WHERE");
                }
                isFirst = false;
            } else {
                sql.append(" " + operator);
            }

            sql.append(" " + expression);

            if (arg != null) {
                args.add(arg);
            }
        }
        return this;
    }

    /**
     * 增加AND查询条件
     *
     * @param expression   表达式
     * @param precondition 先决条件
     */
    public SqlCreator and(String expression, boolean precondition) {
        return addExpression("AND", expression, precondition);
    }

    /**
     * 增加AND查询条件
     *
     * @param expression 表达式
     * @param arg        参数的值
     */
    public SqlCreator and(String expression, Object arg) {
        return addExpression("AND", expression, arg, true);
    }

    /**
     * 增加AND查询条件
     *
     * @param expression   表达式
     * @param arg          参数的值
     * @param precondition 先决条件
     */
    public SqlCreator and(String expression, Object arg, boolean precondition) {
        return addExpression("AND", expression, arg, precondition);
    }

    /**
     * 增加 AND IN 查询条件，比如AND id IN (?, ?, ?);
     *
     * @param columnName   列名称，比如 id
     * @param argList      参数的值数组，比如 new String[] {"1", "2", "3"}
     * @param precondition 先决条件
     */
    public <T> SqlCreator andIn(String columnName, List<T> argList, boolean precondition) {
        if (precondition && null != argList && argList.size() > 0) {
            if (isFirst) {
                if (hasWhere) {
                    if (!sql.toString().toLowerCase().endsWith("where")) {
                        sql.append(" AND");
                    }
                } else {
                    sql.append(" WHERE");
                }
                sql.append(" ");
                isFirst = false;
            } else {
                sql.append(" AND ");
            }

            sql.append(columnName);
            sql.append(" IN ");
            sql.append(getInSql(argList.size()));
            for (int i = 0; i < argList.size(); i++) {
                this.args.add(argList.get(i));
            }
        }
        return this;
    }

    /**
     * 增加OR查询条件
     *
     * @param expression   表达式
     * @param precondition 先决条件
     */
    public SqlCreator or(String expression, boolean precondition) {
        return addExpression("OR", expression, precondition);
    }

    /**
     * 增加OR查询条件
     *
     * @param expression   表达式
     * @param arg          参数的值
     * @param precondition 先决条件
     */
    public SqlCreator or(String expression, Object arg, boolean precondition) {
        return addExpression("OR", expression, arg, precondition);
    }

    /**
     * 添加 GROUP BY 语句。
     *
     * @param columnNames 列名
     */
    public SqlCreator groupBy(String... columnNames) {
        if (columnNames == null || columnNames.length == 0 || (columnNames.length == 1 && columnNames[0] == null)) {
            return this;
        }

        sql.append(" GROUP BY ");
        for (String columnName : columnNames) {
            sql.append(columnName).append(", ");
        }
        sql.delete(sql.length() - 2, sql.length() - 1);
        return this;
    }

    /**
     * 升序排序
     *
     * @param columnName 列名
     */
    public SqlCreator orderBy(String columnName) {
        return orderBy(columnName, false);
    }

    /**
     * 降序排序
     *
     * @param columnName 列名
     */
    public SqlCreator orderByDesc(String columnName) {
        return orderBy(columnName, true);
    }

    /**
     * 排序
     *
     * @param columnName 列名
     * @param isDesc     是否降序
     */
    public SqlCreator orderBy(String columnName, boolean isDesc) {
        if (!hasOrderBy) {
            sql.append(" ORDER BY ");
        } else {
            sql.append(", ");
        }

        sql.append(columnName);
        if (isDesc) {
            sql.append(" DESC");
        }

        hasOrderBy = true;
        return this;
    }

    public SqlCreator limit(int offset, int pageSize) {
        sql.append(" LIMIT " + offset + ", " + pageSize);
        return this;
    }

    public SqlCreator page(PageQuery pageQuery, boolean precondition) {
        if (!precondition) {
            return this;
        }

        if (null == pageQuery) {
            return this;
        }

        if (CollectionUtils.isNotEmpty(pageQuery.getOrderByList())) {
            for (PageOrderBy orderBy : pageQuery.getOrderByList()) {
                orderBy(orderBy.getFieldName(), orderBy.getDesc());
            }
        }

        return limit(pageQuery.getOffset(), pageQuery.getPageSize());
    }

    /**
     * 取得所有参数的值数组
     *
     * @return 所有参数的值数组
     */
    public Object[] getArgs() {
        return args.toArray();
    }

    /**
     * 取得最后生成查询sql
     *
     * @return 查询sql
     */
    public String getSql() {
        return sql.toString();
    }

    private static String getInSql(int size) {
        StringBuilder inSql = new StringBuilder();

        inSql.append("(");
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                inSql.append("?");
            } else {
                inSql.append(",?");
            }
        }
        inSql.append(")");

        return inSql.toString();
    }

}
