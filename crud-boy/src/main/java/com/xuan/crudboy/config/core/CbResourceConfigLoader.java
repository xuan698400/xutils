package com.xuan.crudboy.config.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;

import com.xuan.crudboy.config.model.CbConfig;
import com.xuan.crudboy.config.CbConfigLoader;
import com.xuan.crudboy.config.model.CbTableFieldTypeEnum;
import com.xuan.crudboy.exception.CbAssert;
import com.xuan.crudboy.exception.CbExceptionFactory;

/**
 * @author xuan
 * @since 2023/11/5
 */
public class CbResourceConfigLoader implements CbConfigLoader {

    /**
     * 读流时结束符号
     */
    private static final int EOF = -1;

    /**
     * 读流时缓冲大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private static final String CONFIG_PATH = "crud_config.json";

    private static final String ENCODING = "UTF-8";

    @Override
    public CbConfig load() {
        CbConfig config = JSON.parseObject(readToString(), CbConfig.class);
        checkConfig(config);
        return config;
    }

    private void checkConfig(CbConfig config) {
        CbAssert.notNull(config, "config不能为空");
        CbAssert.notEmpty(config.getTables(), "tables不能为空");
        config.getTables().forEach(table -> {
            CbAssert.notNull(table, "table不能为空");
            CbAssert.notEmpty(table.getName(), "table.name不能为空");
            CbAssert.notEmpty(table.getFieldConfigs(), "table.fieldConfigs不能为空");
            table.getFieldConfigs().forEach(field -> {
                CbAssert.notNull(field, "field不能为空");
                CbAssert.notNull(field.getName(), "field.name不能为空");
                CbAssert.notNull(field.getType(), "field.type不能为空");
                CbTableFieldTypeEnum fieldType = CbTableFieldTypeEnum.codeOf(field.getType());
                CbAssert.notNull(fieldType, String.format("字段类型[%s]找不到枚举", field.getType()));
            });
        });
    }

    private String readToString() {
        InputStream inputStream = null;
        try {
            inputStream = CbResourceConfigLoader.class.getClassLoader().getResourceAsStream(
                CONFIG_PATH);

            InputStreamReader in = new InputStreamReader(inputStream, Charset.forName(ENCODING));
            int n;
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            while (EOF != (n = in.read(buffer))) {
                builder.append(buffer, 0, n);
            }
            return builder.toString();
        } catch (IOException e) {
            throw CbExceptionFactory.bizException("Read config exception.");
        } finally {
            closeQuietly(inputStream);
        }
    }

    private void closeQuietly(Closeable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

}
