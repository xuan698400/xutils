package com.xuan.dao;

import javax.annotation.Resource;

import com.xuan.dao.model.UserDO;
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

    @Test
    public void insert() {
        UserDO userDO = new UserDO();
        userDO.setId(13L);
        userDO.setUsername("xuan");

        dao.insert(userDO);
    }

}
