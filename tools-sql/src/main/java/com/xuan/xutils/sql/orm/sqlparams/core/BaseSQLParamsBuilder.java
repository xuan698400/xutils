package com.xuan.xutils.sql.orm.sqlparams.core;

import java.lang.reflect.Field;

import com.xuan.xutils.base.exception.ExceptionFactory;
import com.xuan.xutils.sql.orm.core.CamelUtils;
import com.xuan.xutils.sql.orm.model.DataModel;
import com.xuan.xutils.sql.orm.model.NameValuePair;
import com.xuan.xutils.sql.orm.sqlparams.SQLParamsBuilder;

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
        return CamelUtils.camelToUnderline(field.getName());
    }

}
