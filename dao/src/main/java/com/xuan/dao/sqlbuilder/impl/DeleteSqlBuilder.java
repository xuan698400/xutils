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
public class DeleteSqlBuilder extends BaseSqlBuilder {

    @Override
    public SqlModel getSql(DataModel dataModel) {

        List<Object> valueList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("DELETE FROM " + dataModel.tableName() + " WHERE ");

        Field[] fields = dataModel.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(dataModel);
            } catch (IllegalAccessException e) {
                throw new BizException("DeleteSqlBuilder_getSql_field_get", e.getMessage(), e);
            }

            if (null == value) {
                continue;
            }

            String fieldName = getFieldName(field);
            sb.append(fieldName);
            sb.append("=? AND ");
            valueList.add(value);
        }

        if (!sb.toString().endsWith(" AND ")) {
            throw new BizException("DeleteSqlBuilder_getSql", "没有删除条件不允许操作");
        }

        SqlModel sqlModel = new SqlModel();
        sqlModel.setSql(sb.toString().substring(0, sb.length() - 5));
        sqlModel.setParams(valueList);
        return sqlModel;
    }

}
