package com.xuan.moho.sql.orm.sqlparams.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.moho.base.exception.ExceptionFactory;
import com.xuan.moho.sql.orm.model.DataModel;
import com.xuan.moho.sql.orm.sqlparams.SQLParams;

/**
 * @author xuan
 * @since 2022/1/26
 */
public class SelectSQLParamsBuilder extends BaseSQLParamsBuilder {

    @Override
    public SQLParams getSQLParams(DataModel dataModel) {
        StringBuilder sb = new StringBuilder("SELECT * FROM " + dataModel.tableName() + " WHERE ");
        List<Object> valueList = new ArrayList<>();

        Field[] fields = dataModel.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(dataModel);
            } catch (IllegalAccessException e) {
                throw ExceptionFactory.bizException("field get IllegalAccessException", e);
            }

            if (null == value) {
                continue;
            }

            String fieldName = getFieldName(field);
            sb.append(fieldName);
            sb.append("=? AND ");
            valueList.add(value);
        }

        String sql;
        if (sb.toString().endsWith(" AND ")) {
            sql = sb.toString().substring(0, sb.length() - 5);
        } else {
            sql = sb.toString().substring(0, sb.length() - 7);
        }

        SQLParams sqlParams = new SQLParams();
        sqlParams.setSql(sql);
        sqlParams.setParams(valueList);
        return sqlParams;
    }

}
