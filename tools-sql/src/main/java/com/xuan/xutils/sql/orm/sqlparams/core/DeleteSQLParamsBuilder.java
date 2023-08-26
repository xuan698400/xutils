package com.xuan.xutils.sql.orm.sqlparams.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.xutils.base.exception.ExceptionFactory;
import com.xuan.xutils.sql.orm.model.DataModel;
import com.xuan.xutils.sql.orm.sqlparams.SQLParams;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class DeleteSQLParamsBuilder extends BaseSQLParamsBuilder {

    @Override
    public SQLParams getSQLParams(DataModel dataModel) {

        List<Object> valueList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("DELETE FROM " + dataModel.tableName() + " WHERE ");

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

        //无条件删除，如果需要可以去掉
        if (!sb.toString().endsWith(" AND ")) {
            throw ExceptionFactory.bizException("It is not allowed to delete without deletion conditions.");
        }

        SQLParams sqlParams = new SQLParams();
        sqlParams.setSql(sb.toString().substring(0, sb.length() - 5));
        sqlParams.setParams(valueList);
        return sqlParams;
    }

}
