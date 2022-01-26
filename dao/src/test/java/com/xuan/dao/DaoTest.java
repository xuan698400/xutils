package com.xuan.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import com.xuan.dao.common.PageData;
import com.xuan.dao.common.PageQuery;
import com.xuan.dao.sqlbuilder.SqlCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xuan
 * @since 2021/11/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DaoTest {

    @Resource
    private Dao dao;

    @Test
    public void test() {
        //先删除
        deleteTest();
        selectTest();
        //新增
        insertTest();
        selectTest();
        //更新
        updateTest();
        selectTest();
        //计数
        countTest();
        //分页查询
        selectPageTest();
    }

    public void insertTest() {
        UserDO u = new UserDO();
        u.setId(1L);
        u.setUsername("xuan_888");
        u.setName("徐工");
        u.setPhone("15858178411");
        u.setEmail("ddd#163.com");
        u.setBizCode("test999");
        u.setCreateTime(new Date());
        u.setIcon("icon");
        u.setModifyTime(new Date());
        u.setFeature("{}");
        u.setPassword("ddd");
        u.setType(1);
        int num = dao.insert(u);
        System.out.println("新增结果：" + num);
    }

    public void deleteTest() {
        //先删除
        UserDO u = new UserDO();
        u.setId(1L);
        int num = dao.delete(u);
        System.out.println("删除结果：" + num);
    }

    public void updateTest() {
        UserDO u = new UserDO();
        u.setId(1L);
        u.setUsername("xuan_888_fix");
        int num = dao.update(u);
        System.out.println("更新结果：" + num);
    }

    public void selectTest() {
        UserDO u = new UserDO();
        u.setId(1L);
        u.setBizCode("test999");
        List<UserDO> u3List = dao.select(u, UserDO.class);
        System.out.println("查询结果：" + JSON.toJSONString(u3List));
    }

    public void countTest() {
        Long num = dao.count(SqlCreator.countTable(new UserDO().tableName()));
        System.out.println("count结果：" + num);
    }

    public void selectPageTest() {
        SqlCreator creator = SqlCreator.selectTable(new UserDO().tableName());
        PageQuery pageQuery = PageQuery.of(2, 1, "modify_time", true, true);
        PageData<UserDO> pageData = dao.selectPage(creator, pageQuery, UserDO.class);
        System.out.println("分页结果：" + JSON.toJSONString(pageData));
    }

}
