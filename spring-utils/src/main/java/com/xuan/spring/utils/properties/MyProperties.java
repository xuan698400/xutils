package com.xuan.spring.utils.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xuan
 * @since 2021/7/30
 */
@ConfigurationProperties(
    prefix = "my.properties"
)
public class MyProperties {

    private String a1;

    private String b1;

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

}
