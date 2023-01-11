package com.xuan.moho.sql.orm.sqlparams.core;

import java.lang.reflect.Field;

import com.xuan.moho.base.exception.ExceptionFactory;
import com.xuan.moho.sql.orm.model.DataModel;
import com.xuan.moho.sql.orm.model.NameValuePair;
import com.xuan.moho.sql.orm.sqlparams.SQLParamsBuilder;

/**
 * @author xuan
 * @since 2021/11/13
 */
public abstract class BaseSQLParamsBuilder implements SQLParamsBuilder {

    protected static NameValuePair getAndCheckPrimaryKey(DataModel dataModel) {
        if (null == dataModel || null == dataModel.primaryKey()) {
            throw ExceptionFactory.bizException("dataModel or primaryKey is null");
        }

        NameValuePair promaryKey = dataModel.primaryKey();

        promaryKey.checkValid();

        return promaryKey;
    }

    protected static NameValuePair getValidVersion(DataModel dataModel) {
        if (null == dataModel) {
            throw ExceptionFactory.bizException("dataModel is null");
        }

        NameValuePair version = dataModel.version();
        if (null != version && version.isValid()) {
            return version;
        }
        return null;
    }

    protected static String getFieldName(Field field) {
        return camelTounderline(field.getName());
    }

    private static String camelTounderline(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(c));
        }
        return builder.toString();
    }

}
