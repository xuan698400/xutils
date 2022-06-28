package com.xuan.dao.sqlbuilder.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.common.exception.BizException;
import com.xuan.dao.common.DataModel;
import com.xuan.dao.sqlbuilder.SqlModel;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class InsertSqlBuilder extends BaseSqlBuilder {

    @Override
    public SqlModel getSql(DataModel dataModel) {

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
                throw new BizException("InsertSqlBuilder_getSql_field_get", e.getMessage(), e);
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

        SqlModel sqlModel = new SqlModel();
        sqlModel.setSql(sql.toString());
        sqlModel.setParams(valueList);
        return sqlModel;
    }

}
