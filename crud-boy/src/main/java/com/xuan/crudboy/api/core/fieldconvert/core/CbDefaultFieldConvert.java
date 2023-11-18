package com.xuan.crudboy.api.core.fieldconvert.core;

import java.util.function.Supplier;

import com.xuan.crudboy.api.core.fieldconvert.CbFieldConvert;
import com.xuan.crudboy.config.model.CbTableFieldConfig;
import com.xuan.crudboy.config.model.CbTableFieldTypeEnum;
import com.xuan.crudboy.exception.CbAssert;
import com.xuan.crudboy.exception.CbExceptionFactory;
import com.xuan.crudboy.utils.CbDateUtils;

/**
 * @author xuan
 * @since 2023/11/10
 */
public class CbDefaultFieldConvert implements CbFieldConvert {

    @Override
    public Object convert(CbTableFieldConfig fieldConfig, String value) {

        if (null == value) {
            return null;
        }

        CbTableFieldTypeEnum fieldType = CbTableFieldTypeEnum.codeOf(fieldConfig.getType());
        CbAssert.notNull(fieldType, String.format("字段类型[%s]找不到枚举", fieldConfig.getType()));

        if (CbTableFieldTypeEnum.INT.eq(fieldType)) {
            return doConvert(() -> Integer.parseInt(value), String.format("值[%s]不是合法int", value));
        }

        //
        else if (CbTableFieldTypeEnum.LONG.eq(fieldType)) {
            return doConvert(() -> Long.parseLong(value), String.format("值[%s]不是合法long", value));
        }

        //
        else if (CbTableFieldTypeEnum.DOUBLE.eq(fieldType)) {
            return doConvert(() -> Double.parseDouble(value), String.format("值[%s]不是合法double", value));
        }

        //
        else if (CbTableFieldTypeEnum.STRING.eq(fieldType)) {
            return doConvert(() -> value, String.format("值[%s]不是合法double", value));
        }

        //
        else if (CbTableFieldTypeEnum.DATE.eq(fieldType)) {
            return doConvert(() -> CbDateUtils.string2DateTime(value), String.format("值[%s]不是合法double", value));
        }

        //
        else {
            throw CbExceptionFactory.bizException(String.format("字段类型[%s]找不到转换逻辑", fieldConfig.getType()));
        }
    }

    private Object doConvert(Supplier<Object> supplier, String exceptionMsg) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw CbExceptionFactory.bizException(exceptionMsg);
        }
    }

}
