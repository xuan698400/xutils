package com.xuan.crudboy.config.model;

/**
 * @author xuan
 * @since 2023/11/5
 */
public enum CbTableFieldTypeEnum {

    //
    INT("int", "int"),
    LONG("long", "long"),
    DOUBLE("double", "double"),
    STRING("string", "String"),
    DATE("date", "date"),
    ;

    private String code;

    private String name;

    CbTableFieldTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public boolean eq(CbTableFieldTypeEnum other) {
        if (null == other) {
            return false;
        }
        return this.getCode().equals(other.getCode());
    }

    public static CbTableFieldTypeEnum codeOf(String code) {
        if (code == null) {
            return null;
        }
        for (CbTableFieldTypeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
