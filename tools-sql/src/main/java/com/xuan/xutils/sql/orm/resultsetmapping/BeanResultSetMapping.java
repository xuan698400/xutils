package com.xuan.xutils.sql.orm.resultsetmapping;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuan.xutils.sql.executer.ResultSetMapping;
import com.xuan.xutils.sql.orm.core.CamelUtils;
import com.xuan.xutils.sql.orm.core.JdbcUtils;

/**
 * 返回JavaBean结果映射实现
 *
 * @author xuan
 * @since 2023/1/11
 */
public class BeanResultSetMapping<T> implements ResultSetMapping<T> {

    private Class<T> beanClass;

    public BeanResultSetMapping(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public T extract(ResultSet rs) throws SQLException {

        //创建实例对象
        Object bean;
        try {
            bean = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("beanClass newInstance exception", e);
        }

        //遍历对象属性，从rs获取值
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Class<?> fieldClazzType = field.getType();
            String columnName = CamelUtils.camelToUnderline(fieldName);
            try {
                field.set(bean, JdbcUtils.getResultSetValue(rs, columnName, fieldClazzType));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("field set IllegalAccessException", e);
            }
        }

        return (T)bean;
    }

}
