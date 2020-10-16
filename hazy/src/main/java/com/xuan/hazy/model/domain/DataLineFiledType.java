package com.xuan.hazy.model.domain;

/**
 * 属性类型
 *
 * @author xuan
 * @since 2020/1/10
 */
public enum DataLineFiledType {

    //
    INT("INT", "整数int"),
    LONG("LONG", "整数long"),
    STRING("STRING", "字符串"),
    DATE("DATE", "日期"),
    ;

    private String type;
    private String msg;

    DataLineFiledType(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public static DataLineFiledType codeOf(String type) {
        if (null == type) {
            return null;
        }
        for (DataLineFiledType e : DataLineFiledType.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    public boolean eq(DataLineFiledType filedType) {
        if (null == filedType) {
            return false;
        }
        return this.getType().equals(filedType.getType());
    }

}
