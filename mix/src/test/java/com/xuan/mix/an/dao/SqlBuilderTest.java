package com.xuan.mix.an.dao;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import com.xuan.mix.an.dao.sql.SqlBuilderFactory;
import com.xuan.mix.an.dao.sql.SqlModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xuan
 * @since 2022/1/25
 */
public class SqlBuilderTest {

    @Test
    public void test() {
        insertTest();
        updateTest();
        deleteTest();
        selectTest();
    }

    @Test
    public void insertTest() {
        UserPO addModel = new UserPO();

        addModel.setId(1L);
        addModel.setBizCode("test");
        addModel.setCreateTime(new Date());
        SqlModel sqlModel = SqlBuilderFactory.getInsertSqlBuilder().getSql(addModel);
        System.out.println(JSON.toJSONString(sqlModel));

        Assert.assertEquals("INSERT INTO bw_user(id,biz_code,create_time) VALUES(?,?,?)", sqlModel.getSql());
        Assert.assertEquals(sqlModel.getParams().size(), 3);
    }

    @Test
    public void updateTest() {
        UserPO updateModel = new UserPO();
        updateModel.setId(1L);
        updateModel.setBizCode("test11");
        updateModel.setModifyTime(new Date());
        SqlModel sqlModel = SqlBuilderFactory.getUpdateSqlBuilder().getSql(updateModel);
        System.out.println(JSON.toJSONString(sqlModel));
    }

    @Test
    public void deleteTest() {
        //
        UserPO updateModel = new UserPO();
        updateModel.setId(1L);
        updateModel.setBizCode("test11");
        SqlModel sqlModel = SqlBuilderFactory.getDeleteSqlBuilder().getSql(updateModel);
        System.out.println(JSON.toJSONString(sqlModel));
    }

    @Test
    public void selectTest() {
        UserPO selectModel = new UserPO();
        selectModel.setId(1L);
        selectModel.setBizCode("test11");
        SqlModel sqlModel = SqlBuilderFactory.getSelectSqlBuilder().getSql(selectModel);
        System.out.println(JSON.toJSONString(sqlModel));
    }

}
