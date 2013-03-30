package com.xuan.utils.interfaces;

/**
 * SQL执行处理器接口, 用于处理带IN子句的SQL语句中IN中参数过长的问题
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:14:51 $
 */
public interface InSQLProcessor {

    /**
     * 执行SQL的方法.
     * 
     * @param sql
     *            SQL语句
     * @param args
     *            语句中的参数
     */
    public void executeSQL(String sql, Object[] args);

}
