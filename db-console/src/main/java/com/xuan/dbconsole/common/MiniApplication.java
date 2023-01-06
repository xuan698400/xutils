package com.xuan.dbconsole.common;

import java.util.List;

/**
 * @author xuan
 * @since 2022/12/8
 */
public class MiniApplication {

    public static void run(Class<?> applicationClass, String... args) {

        List<String> classNameList = ClassUtil.scanClasses(applicationClass, applicationClass.getPackage().getName());

        for (String className : classNameList) {
            try {
                Class<?> cls = Class.forName(className);
                Bean beanAnnotation = cls.getAnnotation(Bean.class);
                if (null != beanAnnotation) {
                    Object obj = cls.newInstance();
                    String beanName = beanAnnotation.value().trim().length() == 0 ? cls.getName() : beanAnnotation
                        .value();
                    BeanFactory.addBean(beanName, obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("MiniApplication start success.");
    }

}
