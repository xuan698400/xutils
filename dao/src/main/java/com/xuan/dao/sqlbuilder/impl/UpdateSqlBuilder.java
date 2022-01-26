package com.xuan.dao.sqlbuilder.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.dao.common.DataModel;
import com.xuan.dao.common.NameValuePair;
import com.xuan.dao.sqlbuilder.SqlModel;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class UpdateSqlBuilder extends BaseSqlBuilder {

    @Override
    public SqlModel getSql(DataModel dataModel) {

        NameValuePair primaryKey = getAndCheckPrimaryKey(dataModel);
        NameValuePair version = getValidVersion(dataModel);

        List<Object> valueList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("UPDATE " + dataModel.tableName() + " SET ");

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
                //没有设置值，不需要更新
                continue;
            }
            if (fieldName.equals(primaryKey.getName())) {
                //主键不需要更新
                continue;
            }
            if (null != version && fieldName.equals(version.getName())) {
                //设置了version，进行version主动加一操作
                String versionAddSql = fieldName + "=" + fieldName + "+1,";
                sb.append(versionAddSql);
                continue;
            }

            //其他需要更新的字段
            sb.append(fieldName);
            sb.append("=?,");
            valueList.add(value);
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE ");
        sb.append(primaryKey.getName());
        sb.append("=?");

        if (null != version) {
            sb.append(" AND ");
            sb.append(version.getName());
            sb.append("=?");
        }

        valueList.add(primaryKey.getValue());
        if (null != version) {
            valueList.add(version.getValue());
        }

        SqlModel updateSql = new SqlModel();
        updateSql.setSql(sb.toString());
        updateSql.setParams(valueList);
        return updateSql;
    }

}
