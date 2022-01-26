package com.xuan.dao.sqlbuilder;

import java.util.ArrayList;
import java.util.List;

import com.xuan.dao.common.PageQuery;

/**
 * 动态查询 SQL 语句生成工具类
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:35:37 $
 */
public class SqlCreator {

    private final StringBuilder sql;
    private final List<Object> args;
    private final List<Integer> argTypes;
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

    /**
     * 构造方法。
     *
     * @param baseSql 带有 WHERE 关键字的原始 sql
     */
    public SqlCreator(String baseSql) {
        this(baseSql, true);
    }

    /**
     * 构造方法。
     *
     * @param baseSql  原始 sql
     * @param hasWhere 原始 sql 是否带有 WHERE 关键字
     */
    public SqlCreator(String baseSql, boolean hasWhere) {
        if (baseSql == null || baseSql.trim().length() == 0) {
            throw new IllegalArgumentException("baseSql can't be null");
        }

        args = new ArrayList<>();
        argTypes = new ArrayList<>();
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
        return addExpression(operator, expression, arg, Integer.MIN_VALUE, precondition);
    }

    /**
     * 增加查询条件
     *
     * @param operator     操作，比如：AND、OR
     * @param expression   表达式，比如：id=?
     * @param arg          表达式中的参数的值
     * @param argType      表达式中的参数的类型
     * @param precondition 先决条件，当为true时才会增加查询条件，比如 id != null
     */
    public SqlCreator addExpression(String operator, String expression, Object arg, int argType, boolean precondition) {
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

            if (argType != Integer.MIN_VALUE) {
                argTypes.add(argType);
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
     * 增加AND查询条件
     *
     * @param expression   表达式
     * @param arg          参数的值
     * @param argType      参数的类型
     * @param precondition 先决条件
     */
    public SqlCreator and(String expression, Object arg, int argType, boolean precondition) {
        return addExpression("AND", expression, arg, argType, precondition);
    }

    /**
     * 增加 AND IN 查询条件，比如AND id IN (?, ?, ?);
     *
     * @param columnName   列名称，比如 id
     * @param argList      参数的值数组，比如 new String[] {"1", "2", "3"}
     * @param argType      参数的类型
     * @param precondition 先决条件
     */
    public <T> SqlCreator andIn(String columnName, List<T> argList, int argType, boolean precondition) {
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
                argTypes.add(argType);
            }
        }
        return this;
    }

    public <T> SqlCreator andIn(String columnName, List<T> argList, int argType) {
        return andIn(columnName, argList, argType, true);
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
     * 增加OR查询条件
     *
     * @param expression   表达式
     * @param arg          参数的值
     * @param argType      参数的类型
     * @param precondition 先决条件
     */
    public SqlCreator or(String expression, Object arg, int argType, boolean precondition) {
        return addExpression("OR", expression, arg, argType, precondition);
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
        return orderBy(pageQuery.getOrderBy(), pageQuery.isDesc()).limit(pageQuery.getOffset(),
            pageQuery.getPageSize());
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
     * 取得所有参数的类型数组
     *
     * @return 所有参数的类型数组
     */
    public int[] getArgTypes() {
        Integer[] objectTypes = argTypes.toArray(new Integer[argTypes.size()]);
        int[] intTypes = new int[objectTypes.length];
        for (int i = 0; i < objectTypes.length; i++) {
            intTypes[i] = objectTypes[i].intValue();
        }
        return intTypes;
    }

    /**
     * 取得最后生成查询sql
     *
     * @return 查询sql
     */
    public String getSQL() {
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
