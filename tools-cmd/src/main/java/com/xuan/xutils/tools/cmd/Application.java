package com.xuan.xutils.tools.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author xuan
 * @since 2022/8/26
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.warn("启动成功，访问：http://localhost:10000/cmd.html");
    }

}
