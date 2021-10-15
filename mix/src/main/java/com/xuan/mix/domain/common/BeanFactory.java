package com.xuan.mix.domain.common;

import com.xuan.mix.domain.middleware.repository.UserRepository;

/**
 * 显示编码，可以让使用方清楚的知道，有哪些Bean需要注入
 *
 * @author xuan
 * @since 2021/10/15
 */
public class BeanFactory {

    private static UserRepository userRepository;

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static void setUserRepository(UserRepository userRepository) {
        BeanFactory.userRepository = userRepository;
    }

}
