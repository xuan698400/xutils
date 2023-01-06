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
                throw new BizException(ExceptionCode.FIELD_GET_EXCEPTION, e.getMessage(), e);
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
