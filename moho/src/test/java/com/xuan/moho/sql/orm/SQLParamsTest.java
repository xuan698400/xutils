package com.xuan.moho.sql.orm;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import com.xuan.moho.sql.orm.sqlparams.SQLParams;
import com.xuan.moho.sql.orm.sqlparams.SQLParamsBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class SQLParamsTest {

    @Test
    public void insertTest() {
        UserPO addModel = new UserPO();

        addModel.setId(1L);
        addModel.setBizCode("test");
        addModel.setCreateTime(new Date());
        SQLParams sqlParams = SQLParamsBuilderFactory.getInsertSQLParamsBuilder().getSQLParams(addModel);
        System.out.println(JSON.toJSONString(sqlParams));

        Assert.assertEquals("INSERT INTO bw_user(id,biz_code,create_time) VALUES(?,?,?)", sqlParams.getSql());
        Assert.assertEquals(sqlParams.getParams().size(), 3);
    }

    @Test
    public void updateTest() {
        UserPO updateModel = new UserPO();
        updateModel.setId(1L);
        updateModel.setBizCode("test11");
        updateModel.setModifyTime(new Date());
        SQLParams sqlParams = SQLParamsBuilderFactory.getUpdateSQLParamsBuilder().getSQLParams(updateModel);
        System.out.println(JSON.toJSONString(sqlParams));
    }

    @Test
    public void deleteTest() {
        //
        UserPO updateModel = new UserPO();
        updateModel.setId(1L);
        updateModel.setBizCode("test11");
        SQLParams sqlParams = SQLParamsBuilderFactory.getDeleteSQLParamsBuilder().getSQLParams(updateModel);
        System.out.println(JSON.toJSONString(sqlParams));
    }

    @Test
    public void selectTest() {
        UserPO selectModel = new UserPO();
        selectModel.setId(1L);
        selectModel.setBizCode("test11");
        SQLParams sqlParams = SQLParamsBuilderFactory.getSelectSQLParamsBuilder().getSQLParams(selectModel);
        System.out.println(JSON.toJSONString(sqlParams));
    }

}
