package com.xuan.xutils.geek.code.sql;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author xuan
 * @since 2023/9/6
 */
public class GkSqlExecuterTest {

    public static void main(String[] args) throws SQLException {
        //一般情况下系统都会有链接数据库的数据源，可以复用
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("bpmweb");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");

        testDelete(druidDataSource);
        testInsert(druidDataSource);
        testSelect(druidDataSource);
    }

    private static void testDelete(DataSource dataSource) throws SQLException {
        String sql = "DELETE FROM bw_user WHERE id=?";
        int num = GkSqlExecuter.update(dataSource, sql, 10L);
        System.out.println("执行删除，成功行数：" + num);
    }

    private static void testInsert(DataSource dataSource) throws SQLException {
        String sql
            = "INSERT INTO bw_user(id,username,name,phone,email,biz_code,create_time,icon,modify_time,feature,"
            + "password,type) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        long id = GkSqlExecuter.insertBackId(dataSource, sql, 10L, String.valueOf(System.currentTimeMillis()), "徐工",
            "15858178411",
            "ddd#163.com",
            "test999", new Date(), "icon", new Date(), "{}", "ddd", 1);
        System.out.println("执行插入，返回ID：" + id);
    }

    private static void testSelect(DataSource dataSource) throws SQLException {
        String sql = "SELECT * FROM bw_user WHERE id=?";
        List<Map<String, Object>> dataList = GkSqlExecuter.queryList(dataSource, sql, 1L);
        System.out.println("执行查询，查询结果：" + dataList);
    }

}
