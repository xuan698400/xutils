package com.xuan.xutils.design.ioc.core;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Resource;

/**
 * Ioc容器启动
 *
 * @author xuan
 * @since 2022/12/8
 */
public class IocApplication {

    public static void run(Class<?> appClazz, String... args) {
        ApplicationContext context = new ApplicationContext();

        IocApplication.instanceBean(appClazz);

        IocApplication.injectBean();

        IocApplication.initBean(context);

        System.out.println("IocApplication start success.");
    }

    /**
     * 扫描启动Application包下的所有带有Bean注解的Bean，并实例化
     *
     * @param appClazz appClazz
     */
    private static void instanceBean(Class<?> appClazz) {
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

    /**
     * 依赖注入
     */
    private static void injectBean() {
        BeanFactory.getAllBean().forEach((beanName, bean) -> {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                Resource resource = field.getAnnotation(Resource.class);
                if (null != resource) {
                    String injectBeanName = resource.name();
                    if (0 == injectBeanName.trim().length()) {
                        injectBeanName = field.getName();
                    }
                    //优先根据名字获取
                    Object injectObj = BeanFactory.getBean(injectBeanName);
                    if (null == injectObj) {
                        //获取不到再根据类型获取
                        injectObj = BeanFactory.getBean(field.getType());
                    }
                    field.setAccessible(true);
                    try {
                        field.set(bean, injectObj);
                    } catch (Exception e) {
                        throw new RuntimeException("inject bean exception.", e);
                    }
                }
            }
        });
    }

    /**
     * 判断实现了InitBean接口的Bean进行初始化调用
     *
     * @param context context
     */
    private static void initBean(ApplicationContext context) {
        BeanFactory.getAllBean().forEach((beanName, bean) -> {
            if (bean instanceof InitBean) {
                ((InitBean)bean).init(context);
            }
        });
    }

}
