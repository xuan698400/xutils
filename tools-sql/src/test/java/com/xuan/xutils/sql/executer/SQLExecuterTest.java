package com.xuan.xutils.sql.executer;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;

import com.xuan.xutils.sql.executer.core.DefaultSQLExecuter;
import com.xuan.xutils.sql.orm.resultsetmapping.MapResultSetMapping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class SQLExecuterTest {

    private SQLExecuter sqlExecuter;

    @Before
    public void initDao() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("bpmweb");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        sqlExecuter = new DefaultSQLExecuter(druidDataSource);
    }

    @Test
    public void test() throws SQLException {
        testDelete();
        testInsert();
        testSelect();
    }

    @Test
    public void testDelete() throws SQLException {
        String sql = "DELETE FROM bw_user WHERE id=?";
        sqlExecuter.update(sql, 1L);
    }

    @Test
    public void testInsert() throws SQLException {
        String sql
            = "INSERT INTO bw_user(id,username,name,phone,email,biz_code,create_time,icon,modify_time,feature,"
            + "password,type) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        int row = sqlExecuter.update(sql, 1L, String.valueOf(System.currentTimeMillis()), "徐工", "15858178411",
            "ddd#163.com",
            "test999", new Date(), "icon", new Date(), "{}", "ddd", 1);
        System.out.println("结果：" + row);
        Assert.assertEquals(row, 1);
    }

    @Test
    public void testSelect() throws SQLException {
        String sql = "SELECT * FROM bw_user WHERE id=?";
        List<Map<String, Object>> dataList = sqlExecuter.queryList(sql, new MapResultSetMapping(), 1L);
        System.out.println("查询结果：" + JSON.toJSONString(dataList));
        Assert.assertEquals(dataList.size(), 1);
    }

}
