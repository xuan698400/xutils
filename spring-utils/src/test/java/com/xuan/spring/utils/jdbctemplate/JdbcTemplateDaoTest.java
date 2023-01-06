package com.xuan.spring.utils.jdbctemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;

import com.xuan.mix.an.common.model.page.PageData;
import com.xuan.mix.an.common.model.page.PageOrderBy;
import com.xuan.mix.an.common.model.page.PageQuery;
import com.xuan.mix.an.dao.Dao;
import com.xuan.mix.an.dao.sql.SqlCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class JdbcTemplateDaoTest {

    private Dao dao;

    @Before
    public void initDao() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dao = new JdbcTemplateDao(dataSource);
    }

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
        u.setUsername(String.valueOf(System.currentTimeMillis()));
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
        long newId = dao.insertBackId(u);
        System.out.println("新增结果的ID：" + newId);
    }

    public void deleteTest() {
        //删除所有
        //SqlCreator sqlCreator = SqlCreator.deleteTable(new UserDO().tableName());
        //int num2 = repository.update(sqlCreator);
        //System.out.println("删除所有结果：" + num2);

        //先删除
        UserDO u = new UserDO();
        u.setId(1L);
        int num = dao.delete(u);
        Assert.assertEquals(1, num);
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
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageIndex(1);
        pageQuery.setPageSize(2);
        pageQuery.setNeedTotalCount(true);

        PageOrderBy orderBy = new PageOrderBy();
        orderBy.setFieldName("modify_time");
        orderBy.setDesc(true);

        List<PageOrderBy> pageOrderByList = new ArrayList<>();
        pageOrderByList.add(orderBy);
        pageQuery.setOrderByList(pageOrderByList);

        PageData<UserDO> pageData = dao.selectPage(creator, pageQuery, UserDO.class);
        System.out.println("分页结果：" + JSON.toJSONString(pageData));
    }

}
