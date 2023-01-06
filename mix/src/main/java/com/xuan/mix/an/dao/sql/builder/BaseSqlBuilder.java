package com.xuan.mix.an.dao.sql.builder;

import java.lang.reflect.Field;

import com.xuan.mix.an.common.exception.BizException;
import com.xuan.mix.an.common.utils.StringUtils;
import com.xuan.mix.an.dao.common.DataModel;
import com.xuan.mix.an.dao.common.ExceptionCode;
import com.xuan.mix.an.dao.common.NameValuePair;
import com.xuan.mix.an.dao.sql.SqlBuilder;

/**
 * @author xuan
 * @since 2021/11/13
 */
public abstract class BaseSqlBuilder implements SqlBuilder {

    protected static NameValuePair getAndCheckPrimaryKey(DataModel dataModel) {
        if (null == dataModel) {
            throw new BizException(ExceptionCode.PARAM_INVALID, "dataModel is null.");
        }

        NameValuePair primaryKey = dataModel.primaryKey();
        if (null == primaryKey) {
            throw new BizException(ExceptionCode.PARAM_INVALID, "dataModel primaryKey is null.");
        }

        if (StringUtils.isEmpty(primaryKey.getName())) {
            throw new BizException(ExceptionCode.PARAM_INVALID, "dataModel primaryKey name is empty.");
        }

        if (null == primaryKey.getValue()) {
            throw new BizException(ExceptionCode.PARAM_INVALID, "dataModel primaryKey value is null.");
        }

        return primaryKey;
    }

    protected static NameValuePair getValidVersion(DataModel dataModel) {
        if (null == dataModel) {
            throw new BizException("PARAM_INVALID", "dataModel is null.");
        }

        NameValuePair version = dataModel.version();
        if (null != version && StringUtils.isEmpty(version.getName()) && null != version.getValue()) {
            return version;
        }
        return null;
    }

    protected static String getFieldName(Field field) {
        return camel4underline(field.getName());
    }

    private static String camel4underline(String str) {
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
