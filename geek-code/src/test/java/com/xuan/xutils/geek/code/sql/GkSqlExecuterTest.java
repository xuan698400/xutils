package com.xuan.xutils.geek.code.sql;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author xuan
 * @since 2023/9/6
 */
public class GkSqlExecuterTest {

    public static void main(String[] args) throws SQLException {
        //1、一般情况下系统都会有链接数据库的数据源，可以复用
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("bpmweb");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //2、构建一个执行器
        GkSqlExecuter sqlExecuter = new GkSqlExecuter(druidDataSource);

        //3、调用API操作数据库
        testDelete(sqlExecuter);
        testInsert(sqlExecuter);
        testSelectMapList(sqlExecuter);
        testSelectBeanList(sqlExecuter);
    }

    private static void testDelete(GkSqlExecuter sqlExecuter) throws SQLException {
        String sql = "DELETE FROM bw_user WHERE id=?";
        int num = sqlExecuter.update(sql, 10L);
        System.out.println("执行删除，成功行数：" + num);
    }

    private static void testInsert(GkSqlExecuter sqlExecuter) throws SQLException {
        String sql
            = "INSERT INTO bw_user(id,username,name,phone,email,biz_code,create_time,icon,modify_time,feature,"
            + "password,type) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        long id = sqlExecuter.insertBackId(sql, 10L, String.valueOf(System.currentTimeMillis()), "徐工",
            "15858178411",
            "ddd#163.com",
            "test999", new Date(), "icon", new Date(), "{}", "ddd", 1);
        System.out.println("执行插入，返回ID：" + id);
    }

    private static void testSelectMapList(GkSqlExecuter sqlExecuter) throws SQLException {
        String sql = "SELECT * FROM bw_user WHERE id=?";
        List<Map<String, Object>> dataList = sqlExecuter.queryMapList(sql, 1L);
        System.out.println("执行查询，查询结果：" + dataList);
    }

    private static void testSelectBeanList(GkSqlExecuter sqlExecuter) throws SQLException {
        String sql = "SELECT * FROM bw_user WHERE id=?";
        List<User> dataList = sqlExecuter.queryBeanList(User.class, sql, 1L);
        System.out.println("执行查询，查询结果id：" + dataList.get(0).getId());
        System.out.println("执行查询，查询结果name：" + dataList.get(0).getName());
    }

    public final static class User {

        private String name;

        private Long id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    }

}
