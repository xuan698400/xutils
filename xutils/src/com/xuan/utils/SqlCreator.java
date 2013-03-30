package com.xuan.utils;

import java.util.ArrayList;
import java.util.List;

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
    private boolean hasWhere = true;
    private boolean isFirst = true;

    /**
     * 构造方法。
     * 
     * @param baseSQL
     *            带有 WHERE 关键字的原始 sql
     */
    public SqlCreator(String baseSQL) {
        this(baseSQL, true);
    }

    /**
     * 构造方法。
     * 
     * @param baseSQL
     *            原始 sql
     * @param hasWhere
     *            原始 sql 是否带有 WHERE 关键字
     */
    public SqlCreator(String baseSQL, boolean hasWhere) {
        if (Validators.isEmpty(baseSQL)) {
            throw new IllegalArgumentException("baseSQL can't be null");
        }

        args = new ArrayList<Object>();
        argTypes = new ArrayList<Integer>();
        sql = new StringBuilder();
        sql.append(baseSQL.trim());
        this.hasWhere = hasWhere;
    }

    /**
     * 增加查询条件
     * 
     * @param operator
     *            操作，比如：AND、OR
     * @param expression
     *            表达式，比如：id=1
     * @param precondition
     *            先决条件，当为true时才会增加查询条件，比如 user != null
     */
    public void addExpression(String operator, String expression, boolean precondition) {
        addExpression(operator, expression, null, precondition);
    }

    /**
     * 增加查询条件
     * 
     * @param operator
     *            操作，比如：AND、OR
     * @param expression
     *            表达式，比如：id=?
     * @param arg
     *            表达式中的参数的值
     * @param precondition
     *            先决条件，当为true时才会增加查询条件，比如 id != null
     */
    public void addExpression(String operator, String expression, Object arg, boolean precondition) {
        addExpression(operator, expression, arg, Integer.MIN_VALUE, precondition);
    }

    /**
     * 增加查询条件
     * 
     * @param operator
     *            操作，比如：AND、OR
     * @param expression
     *            表达式，比如：id=?
     * @param arg
     *            表达式中的参数的值
     * @param argType
     *            表达式中的参数的类型
     * @param precondition
     *            先决条件，当为true时才会增加查询条件，比如 id != null
     */
    public void addExpression(String operator, String expression, Object arg, int argType, boolean precondition) {
        if (precondition) {
            if (isFirst) {
                if (hasWhere) {
                    if (!sql.toString().toLowerCase().endsWith("where")) {
                        sql.append(" " + operator);
                    }
                }
                else {
                    sql.append(" WHERE");
                }
                isFirst = false;
            }
            else {
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
    }

    /**
     * 增加AND查询条件
     * 
     * @param expression
     *            表达式
     * @param precondition
     *            先决条件
     */
    public void and(String expression, boolean precondition) {
        addExpression("AND", expression, precondition);
    }

    /**
     * 增加AND查询条件
     * 
     * @param expression
     *            表达式
     * @param arg
     *            参数的值
     * @param precondition
     *            先决条件
     */
    public void and(String expression, Object arg, boolean precondition) {
        addExpression("AND", expression, arg, precondition);
    }

    /**
     * 增加AND查询条件
     * 
     * @param expression
     *            表达式
     * @param arg
     *            参数的值
     * @param argType
     *            参数的类型
     * @param precondition
     *            先决条件
     */
    public void and(String expression, Object arg, int argType, boolean precondition) {
        addExpression("AND", expression, arg, argType, precondition);
    }

    /**
     * 增加 AND IN 查询条件，比如AND id IN (?, ?, ?);
     * 
     * @param columnName
     *            列名称，比如 id
     * @param args
     *            参数的值数组，比如 new String[] {"1", "2", "3"}
     * @param argType
     *            参数的类型
     * @param precondition
     *            先决条件
     */
    public void andIn(String columnName, Object[] args, int argType, boolean precondition) {
        if (precondition && args.length > 0) {
            if (isFirst) {
                if (hasWhere) {
                    if (!sql.toString().toLowerCase().endsWith("where")) {
                        sql.append(" AND");
                    }
                }
                else {
                    sql.append(" WHERE");
                }
                sql.append(" ");
                isFirst = false;
            }
            else {
                sql.append(" AND ");
            }

            sql.append(columnName);
            sql.append(" IN ");
            sql.append(JdbcUtils.getInSQL(args.length));
            for (int i = 0; i < args.length; i++) {
                this.args.add(args[i]);
                argTypes.add(argType);
            }
        }
    }

    /**
     * 增加OR查询条件
     * 
     * @param expression
     *            表达式
     * @param precondition
     *            先决条件
     */
    public void or(String expression, boolean precondition) {
        addExpression("OR", expression, precondition);
    }

    /**
     * 增加OR查询条件
     * 
     * @param expression
     *            表达式
     * @param arg
     *            参数的值
     * @param precondition
     *            先决条件
     */
    public void or(String expression, Object arg, boolean precondition) {
        addExpression("OR", expression, arg, precondition);
    }

    /**
     * 增加OR查询条件
     * 
     * @param expression
     *            表达式
     * @param arg
     *            参数的值
     * @param argType
     *            参数的类型
     * @param precondition
     *            先决条件
     */
    public void or(String expression, Object arg, int argType, boolean precondition) {
        addExpression("OR", expression, arg, argType, precondition);
    }

    /**
     * 添加 GROUP BY 语句。
     * 
     * @param columnNames
     *            列名
     */
    public void groupBy(String... columnNames) {
        if (Validators.isEmpty(columnNames)) {
            return;
        }

        sql.append(" GROUP BY ");
        for (String columnName : columnNames) {
            sql.append(columnName).append(", ");
        }
        sql.delete(sql.length() - 2, sql.length() - 1);
    }

    /**
     * 升序排序
     * 
     * @param columnName
     *            列名
     */
    public void orderBy(String columnName) {
        orderBy(columnName, false);
    }

    /**
     * 降序排序
     * 
     * @param columnName
     *            列名
     */
    public void orderByDesc(String columnName) {
        orderBy(columnName, true);
    }

    /**
     * 排序
     * 
     * @param columnName
     *            列名
     * @param isDesc
     *            是否降序
     */
    public void orderBy(String columnName, boolean isDesc) {
        if (!hasOrderBy) {
            sql.append(" ORDER BY ");
        }
        else {
            sql.append(", ");
        }

        sql.append(columnName);
        if (isDesc) {
            sql.append(" DESC");
        }

        hasOrderBy = true;
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

}
