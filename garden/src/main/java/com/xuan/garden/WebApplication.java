package com.xuan.garden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuan
 * @since 2020/11/19
 */
@SpringBootApplication
public class WebApplication {

    private final static Logger log = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        log.warn("garden start success...");
    }

}
