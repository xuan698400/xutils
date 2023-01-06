package com.xuan.mix.an.miniapp;

import java.util.List;

/**
 * @author xuan
 * @since 2022/12/8
 */
public class MiniApplication {

    public static void run(Class<?> appClazz, String... args) {
        ApplicationContext context = new ApplicationContext();

        MiniApplication.createBean(appClazz);

        MiniApplication.initBean(context);

        System.out.println("MiniApplication start success.");
    }

    private static void createBean(Class<?> appClazz) {
        List<String> clazzNameList = ClassUtil.scanClasses(appClazz);

        for (String clazzName : clazzNameList) {
            try {
                Class<?> cls = Class.forName(clazzName);
                Bean beanAnnotation = cls.getAnnotation(Bean.class);
                if (null != beanAnnotation) {
                    Object obj = cls.newInstance();
                    String beanName = beanAnnotation.value().trim().length() == 0 ? cls.getName() : beanAnnotation
                        .value();
                    BeanFactory.addBean(beanName, obj);
                }
            } catch (Exception e) {
                throw new RuntimeException("init bean exception.", e);
            }
        }
    }

    private static void initBean(ApplicationContext context) {

        BeanFactory.getAllBean().forEach((k, v) -> {
            if (v instanceof InitBean) {
                ((InitBean)v).init(context);
            }
        });
    }

}
