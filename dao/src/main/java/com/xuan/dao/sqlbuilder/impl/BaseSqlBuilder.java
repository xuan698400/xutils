package com.xuan.dao.sqlbuilder.impl;

import java.lang.reflect.Field;

import com.xuan.common.exception.BizException;
import com.xuan.common.utils.StringUtils;
import com.xuan.dao.common.DataModel;
import com.xuan.dao.common.NameValuePair;
import com.xuan.dao.sqlbuilder.SqlBuilder;

/**
 * @author xuan
 * @since 2021/11/13
 */
public abstract class BaseSqlBuilder implements SqlBuilder {

    protected static NameValuePair getAndCheckPrimaryKey(DataModel dataModel) {
        NameValuePair primaryKey = dataModel.primaryKey();
        if (null == primaryKey) {
            throw new BizException("BaseSqlBuilder_getAndCheckPrimaryKey.", "获取主键信息DataModel.primaryKey为null");
        }

        if (StringUtils.isEmpty(primaryKey.getName())) {
            throw new BizException("BaseSqlBuilder_getAndCheckPrimaryKey.", "获取主键信息DataModel.primaryKey.getName为空");
        }
        return primaryKey;
    }

    protected static NameValuePair getValidVersion(DataModel dataModel) {
        NameValuePair version = dataModel.version();
        if (null != version && null != version.getName() && null != version.getValue()) {
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
