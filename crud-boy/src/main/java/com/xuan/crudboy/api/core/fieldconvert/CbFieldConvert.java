package com.xuan.crudboy.api.core.fieldconvert;

import com.xuan.crudboy.config.model.CbTableFieldConfig;

/**
 * @author xuan
 * @since 2023/11/10
 */
public interface CbFieldConvert {

    Object convert(CbTableFieldConfig fieldConfig, String value);
}
