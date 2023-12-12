package com.xuan.xutils.sql.orm.sqlparams.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.xutils.sql.orm.model.DataModel;
import com.xuan.xutils.sql.orm.sqlparams.SQLParams;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class InsertSQLParamsBuilder extends BaseSQLParamsBuilder {

    @Override
    public SQLParams getSQLParams(DataModel dataModel) {

        List<Object> valueList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("INSERT INTO " + dataModel.tableName() + "(");
        int paramsSize = 0;

        Field[] fields = dataModel.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(dataModel);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("field get IllegalAccessException", e);
            }

            if (null == value) {
                continue;
            }

            sql.append(getFieldName(field));
            sql.append(",");
            valueList.add(value);
            paramsSize++;
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(") VALUES(");
        for (int i = 0; i < paramsSize; i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        SQLParams sqlParams = new SQLParams();
        sqlParams.setSql(sql.toString());
        sqlParams.setParams(valueList);
        return sqlParams;
    }

}
