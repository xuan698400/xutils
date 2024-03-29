package com.xuan.xutils.sql.orm.sqlparams.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xuan.xutils.sql.orm.model.DataModel;
import com.xuan.xutils.sql.orm.model.NameValuePair;
import com.xuan.xutils.sql.orm.sqlparams.SQLParams;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class UpdateSQLParamsBuilder extends BaseSQLParamsBuilder {

    @Override
    public SQLParams getSQLParams(DataModel dataModel) {
        NameValuePair primaryKey = getAndCheckPrimaryKey(dataModel);
        NameValuePair version = getValidVersion(dataModel);

        List<Object> valueList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("UPDATE " + dataModel.tableName() + " SET ");

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

            String fieldName = getFieldName(field);
            if (fieldName.equals(primaryKey.getName())) {
                continue;
            }

            if (null != version && fieldName.equals(version.getName())) {
                //设置了version，进行version主动加一操作，一般用来做乐观锁
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

        //加上住键条件
        sb.append(primaryKey.getName());
        sb.append("=?");
        valueList.add(primaryKey.getValue());

        //加上version条件
        if (null != version) {
            sb.append(" AND ");
            sb.append(version.getName());
            sb.append("=?");
            valueList.add(version.getValue());
        }

        SQLParams sqlParams = new SQLParams();
        sqlParams.setSql(sb.toString());
        sqlParams.setParams(valueList);
        return sqlParams;
    }

}
