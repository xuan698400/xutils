package com.xuan.moho.sql.orm;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.xuan.moho.BaseTest;
import com.xuan.moho.base.model.page.PageData;
import com.xuan.moho.base.model.page.PageQuery;
import com.xuan.moho.sql.executer.SQLExecuter;
import com.xuan.moho.sql.executer.core.DefaultSQLExecuter;
import com.xuan.moho.sql.orm.core.DefaultDao;
import com.xuan.moho.sql.orm.sqlparams.SQLParamsQueryCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/12
 */
public class DaoTest extends BaseTest {

    private Dao dao;

    @Before
    public void initDao() {
        SQLExecuter sqlExecuter = new DefaultSQLExecuter(dataSource);
        dao = new DefaultDao(sqlExecuter);
    }

    @Test
    public void testInsertBackId() throws SQLException {
        Long newId = insertUser();
        //查询
        UserPO condition = new UserPO();
        condition.setId(newId);
        List<UserPO> queryUser = dao.select(condition, UserPO.class);
        System.out.println(JSON.toJSONString(queryUser));
        Assert.assertEquals(queryUser.get(0).getId(), newId);
        //
        deleteUser(newId);
    }

    @Test
    public void testSelect() throws SQLException {
        UserPO query = new UserPO();
        query.setId(1L);

        List<UserPO> userList = dao.select(query, UserPO.class);
        System.out.println(JSON.toJSONString(userList));
    }

    @Test
    public void testCount() throws SQLException {
        SQLParamsQueryCreator creator = SQLParamsQueryCreator.selectTable("bw_user").and("id=?", 1L);
        Long num = dao.count(creator);
        System.out.println(num);
    }

    @Test
    public void testSelectPage() throws SQLException {
        SQLParamsQueryCreator creator = SQLParamsQueryCreator.selectTable("bw_user");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setNeedTotalCount(true);

        PageData<UserPO> pageData = dao.selectPage(creator, pageQuery, UserPO.class);
        System.out.println(JSON.toJSONString(pageData));
    }

    private Long insertUser() throws SQLException {
        UserPO addUser = new UserPO();
        addUser.setBizCode("test666");
        addUser.setUsername(String.valueOf(System.currentTimeMillis()));
        Long newId = dao.insertBackId(addUser);
        System.out.println("newId:" + newId);
        return newId;
    }

    private void deleteUser(Long id) throws SQLException {
        UserPO condition = new UserPO();
        condition.setId(id);
        dao.delete(condition);
    }

}
