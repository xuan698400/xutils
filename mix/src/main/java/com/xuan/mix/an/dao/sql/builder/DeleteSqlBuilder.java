package com.xuan.mix.an.dao.sql.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.mix.an.common.exception.BizException;
import com.xuan.mix.an.dao.common.DataModel;
import com.xuan.mix.an.dao.common.ExceptionCode;
import com.xuan.mix.an.dao.sql.SqlModel;

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
                throw new BizException(ExceptionCode.FIELD_GET_EXCEPTION, e.getMessage(), e);
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
            throw new BizException(ExceptionCode.PARAM_INVALID,
                "It is not allowed to delete without deletion conditions.");
        }

        SqlModel sqlModel = new SqlModel();
        sqlModel.setSql(sb.toString().substring(0, sb.length() - 5));
        sqlModel.setParams(valueList);
        return sqlModel;
    }

}
