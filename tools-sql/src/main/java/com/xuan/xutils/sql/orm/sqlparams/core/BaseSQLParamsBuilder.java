package com.xuan.xutils.sql.orm.sqlparams.core;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;

import com.xuan.xutils.sql.orm.core.CamelUtils;
import com.xuan.xutils.sql.orm.model.DataModel;
import com.xuan.xutils.sql.orm.model.NameValuePair;
import com.xuan.xutils.sql.orm.sqlparams.SQLParamsBuilder;

/**
 * 根据数据模型构建SQL参数基类
 *
 * @author xuan
 * @since 2021/11/13
 */
public abstract class BaseSQLParamsBuilder implements SQLParamsBuilder {

    protected static NameValuePair getAndCheckPrimaryKey(DataModel dataModel) {
        if (null == dataModel) {
            throw new InvalidParameterException("dataModel is null.");
        }
        if (null == dataModel.primaryKey()) {
            throw new InvalidParameterException("primaryKey is null.");
        }

        NameValuePair promaryKey = dataModel.primaryKey();

        promaryKey.checkValid();

        return promaryKey;
    }

    protected static NameValuePair getValidVersion(DataModel dataModel) {
        if (null == dataModel) {
            throw new InvalidParameterException("dataModel is null.");
        }

        NameValuePair version = dataModel.version();
        if (null != version && version.isValid()) {
            return version;
        }
        return null;
    }

    protected static String getFieldName(Field field) {
        return CamelUtils.camelToUnderline(field.getName());
    }

}
