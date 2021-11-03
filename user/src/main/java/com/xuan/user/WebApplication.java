package com.xuan.user;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

import com.xuan.user.dao.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuan
 * @since 2020/3/9
 */
@SpringBootApplication
public class WebApplication {

    private final static Logger log = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(WebApplication.class, args);
        //log.warn("Application start success...");

        UserDO userDO = new UserDO();
        userDO.setName("ddd");
        Serializable s = userDO;
        System.out.println(JSON.toJSONString(s));
    }


}
