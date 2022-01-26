package com.xuan.dao.sqlbuilder.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.dao.common.DataModel;
import com.xuan.dao.sqlbuilder.SqlModel;

/**
 * @author xuan
 * @since 2022/1/26
 */
public class SelectSqlBuilder extends BaseSqlBuilder {

    @Override
    public SqlModel getSql(DataModel dataModel) {
        StringBuilder sb = new StringBuilder("SELECT * FROM " + dataModel.tableName() + " WHERE ");
        List<Object> valueList = new ArrayList<>();

        Field[] fields = dataModel.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(dataModel);
            } catch (IllegalAccessException e) {
                //Ignore
            }

            String fieldName = getFieldName(field);
            if (null == value) {
                //没有设置值，不需要条件
                continue;
            }

            sb.append(fieldName);
            sb.append("=? AND ");
            valueList.add(value);
        }

        SqlModel sqlModel = new SqlModel();
        sqlModel.setSql(sb.toString().substring(0, sb.length() - 5));
        sqlModel.setParams(valueList);
        return sqlModel;
    }

}
