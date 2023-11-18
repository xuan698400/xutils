package com.xuan.crudboy.domain;

import java.util.ArrayList;
import java.util.List;

import com.xuan.crudboy.config.model.CbTableConfig;

/**
 * @author xuan
 * @since 2023/11/9
 */
public class CbTable {

    private CbTableConfig config;

    private List<CbTableField> fields;

    public CbSqlParams buildInsertSqlParams() {

        List<Object> valueList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("INSERT INTO " + config.getName() + "(");
        int paramsSize = 0;

        for (CbTableField field : fields) {
            Object value = field.getValue();
            if (null == value) {
                continue;
            }
            sql.append(camelToUnderline(field.getConfig().getName()));
            sql.append(",");
            valueList.add(value);
            paramsSize++;
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(") VALUES(");
        for (int i = 0; i < paramsSize; i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        CbSqlParams sqlParams = new CbSqlParams();
        sqlParams.setSql(sql.toString());
        sqlParams.setParams(valueList);
        return sqlParams;
    }

    private String camelToUnderline(String str) {
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

    public CbTableConfig getConfig() {
        return config;
    }

    public void setConfig(CbTableConfig config) {
        this.config = config;
    }

    public List<CbTableField> getFields() {
        return fields;
    }

    public void setFields(List<CbTableField> fields) {
        this.fields = fields;
    }

}
