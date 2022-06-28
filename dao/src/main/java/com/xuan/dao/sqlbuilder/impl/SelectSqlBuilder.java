package com.xuan.dao.sqlbuilder.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.common.exception.BizException;
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
            Object value;
            try {
                value = field.get(dataModel);
            } catch (IllegalAccessException e) {
                throw new BizException("SelectSqlBuilder_getSql_field_get", e.getMessage(), e);
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

        SqlModel sqlModel = new SqlModel();
        sqlModel.setSql(sql);
        sqlModel.setParams(valueList);
        return sqlModel;
    }

}
