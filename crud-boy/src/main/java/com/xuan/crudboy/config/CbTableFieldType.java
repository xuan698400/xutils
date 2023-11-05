package com.xuan.crudboy.config;

/**
 * @author xuan
 * @since 2023/11/5
 */
public enum CbTableFieldType {

    //
    INT("int"),
    LONG("long"),
    DOUBLE("double"),
    STRING("string"),
    JSON_MAP("json.map"),
    DATE("date"),
    ;

    private String code;

    CbTableFieldType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
