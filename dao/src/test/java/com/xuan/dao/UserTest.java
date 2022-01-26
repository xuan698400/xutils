package com.xuan.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

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
public class UserTest {

    @Resource
    private Dao dao;

    //@Test
    //public void insert() {
    //    List<BaseDO> dataList = new ArrayList<>();
    //
    //    UserDO userDO1 = new UserDO();
    //    userDO1.setId(15L);
    //    userDO1.setUsername("xuan1");
    //    userDO1.setName("徐工1");
    //    userDO1.setPhone("15858178400");
    //    userDO1.setEmail("ddd#163.com");
    //    userDO1.setBiz_code("testbizcode");
    //    userDO1.setCreate_time(new Date());
    //    userDO1.setIcon("icon");
    //    userDO1.setModify_time(new Date());
    //    userDO1.setFeature("{}");
    //    userDO1.setPassword("ddd");
    //    userDO1.setType(1);
    //    dataList.add(userDO1);
    //
    //    UserDO userDO2 = new UserDO();
    //    userDO2.setId(16L);
    //    userDO2.setUsername("xuan2");
    //    userDO2.setName("徐工2");
    //    userDO2.setPhone("15858178400");
    //    userDO2.setEmail("ddd#163.com");
    //    userDO2.setBiz_code("testbizcode");
    //    userDO2.setCreate_time(new Date());
    //    userDO2.setIcon("icon");
    //    userDO2.setModify_time(new Date());
    //    userDO2.setFeature("{}");
    //    userDO2.setPassword("ddd");
    //    userDO2.setType(1);
    //    dataList.add(userDO2);
    //
    //    //dao.insert(dataList);
    //}
    //
    //@Test
    //public void update() {
    //    List<BaseDO> dataList = new ArrayList<>();
    //
    //    UserDO userDO1 = new UserDO();
    //    userDO1.setId(15L);
    //    userDO1.setName("徐工1.update1");
    //    userDO1.setPhone("123456.update1");
    //    dataList.add(userDO1);
    //
    //    UserDO userDO2 = new UserDO();
    //    userDO2.setId(16L);
    //    userDO2.setName("徐工1.update2");
    //    userDO2.setPhone("123456.update2");
    //    dataList.add(userDO2);
    //
    //    //dao.update(dataList);
    //}
    //
    //@Test
    //public void delete() {
    //    List<BaseDO> dataList = new ArrayList<>();
    //
    //    UserDO userDO1 = new UserDO();
    //    userDO1.setId(15L);
    //    dataList.add(userDO1);
    //
    //    UserDO userDO2 = new UserDO();
    //    userDO2.setId(16L);
    //    dataList.add(userDO2);
    //
    //    //dao.delete(dataList);
    //}
    //
    //@Test
    //public void select() {
    //    List<UserDO> conditionList = new ArrayList<>();
    //
    //    UserDO userDO1 = new UserDO();
    //    userDO1.setId(15L);
    //    conditionList.add(userDO1);
    //
    //    UserDO userDO2 = new UserDO();
    //    userDO2.setId(16L);
    //    conditionList.add(userDO2);
    //
    //    UserDO userDO3 = new UserDO();
    //    userDO3.setId(999L);
    //    conditionList.add(userDO3);
    //
    //    List<UserDO> dataList = dao.select(conditionList, UserDO.class);
    //    System.out.println(JSON.toJSONString(dataList));
    //}
    //
    //@Test
    //public void select2() {
    //    List<UserDO> conditionList = new ArrayList<>();
    //
    //    UserDO condition = new UserDO();
    //    condition.setBiz_code("testbizcode");
    //    conditionList.add(condition);
    //
    //    List<UserDO> dataList = dao.select(conditionList, UserDO.class);
    //    System.out.println(JSON.toJSONString(dataList));
    //}

}
