package com.xuan.ext.starter;

import com.xuan.ext.framework.core.register.ExtRegister;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class Main {

    public static void main(String[] args) {
        ExtRegister.getInstance().register("com.xuan.ext.platform.sdk");
    }
}
