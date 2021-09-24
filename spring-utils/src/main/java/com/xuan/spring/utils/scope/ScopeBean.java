package com.xuan.spring.utils.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/9/24
 */
@Component
@Scope("thread")
public class ScopeBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
