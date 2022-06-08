package com.xuan.dao.sqlbuilder;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import com.xuan.dao.UserDO;
import com.xuan.dao.common.SqlSyntax;
import org.junit.Test;

/**
 * @author xuan
 * @since 2022/1/25
 */
public class SqlBuilderTest {

    @Test
    public void insertTest() {
        UserDO addModel = new UserDO();
        addModel.setId(1L);
        addModel.setBizCode("test");
        addModel.setCreateTime(new Date());
        SqlModel sqlModel = SqlBuilderFactory.getInsertSqlBuilder(SqlSyntax.MYSQL).getSql(addModel);
        System.out.println(JSON.toJSONString(sqlModel));
    }

    @Test
    public void updateTest() {
        UserDO updateModel = new UserDO();
        updateModel.setId(1L);
        updateModel.setBizCode("test11");
        updateModel.setModifyTime(new Date());
        SqlModel sqlModel = SqlBuilderFactory.getUpdateSqlBuilder(SqlSyntax.MYSQL).getSql(updateModel);
        System.out.println(JSON.toJSONString(sqlModel));
    }

    @Test
    public void deleteTest() {
        //
        UserDO updateModel = new UserDO();
        updateModel.setId(1L);
        updateModel.setBizCode("test11");
        SqlModel sqlModel = SqlBuilderFactory.getDeleteSqlBuilder(SqlSyntax.MYSQL).getSql(updateModel);
        System.out.println(JSON.toJSONString(sqlModel));
    }

    @Test
    public void selectTest() {
        UserDO selectModel = new UserDO();
        selectModel.setId(1L);
        selectModel.setBizCode("test11");
        SqlModel sqlModel = SqlBuilderFactory.getSelectSqlBuilder(SqlSyntax.MYSQL).getSql(selectModel);
        System.out.println(JSON.toJSONString(sqlModel));
    }

}
