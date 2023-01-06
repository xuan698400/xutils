package com.xuan.moho.sql.model;

/**
 * @author xuan
 * @since 2023/1/6
 */
public enum MySqlColumnType implements ColumnType {

    //数值类型
    TINYINT("TINYINT", "小整数值（1字节，-128～127，0～255）"),
    SMALLINT("SMALLINT", "大整数值（2字节，-32768～32767，0～65535）"),
    MEDIUMINT("MEDIUMINT", "大整数值（3字节，-8388608～8388607，0～16777215）"),
    INT("INT", "大整数值（4字节，-2147483648～2147483647，0～4294967295）"),
    BIGINT("BIGINT", "极大整数值（8字节，-9223372036854775808～9223372036854775807，0～18446744073709551615）"),

    //浮点类型
    FLOAT("FLOAT", "单精度浮点数（4字节）"),
    DOUBLE("DOUBLE", "双精度浮点数（8字节）"),
    DECIMAL("DECIMAL", "精确小数值"),

    //字符串类型
    CHAR("CHAR", "定长字符串（0-255字节）"),
    VARCHAR("VARCHAR", "变长字符串（0-65535字节）"),
    TINYTEXT("TINYTEXT", "短文本字符串（0-255字节）"),
    TEXT("TEXT", "长文本数据（0-65535字节）"),
    LONGTEXT("LONGTEXT", "极大文本数据（0-4294967295字节）"),
    TINYBLOB("TINYBLOB", "短二进制（0-255字节）"),
    BLOB("BLOB", "长二进制（0-65535字节）"),
    LONGBLOB("LONGBLOB", "超长二进制（0-4294967295字节）"),

    //日期类型
    YEAR("YEAR", "1字节，年份值，1901～2155，YYYY"),
    TIME("TIME", "3字节，时间值，-838:59:59～838:59:59，hhh:mm:ss或者hh:mm:ss"),
    DATE("DATE", "3字节，日期值，1000-01-01～9999:12:31，YYYY-MM-DD"),
    DATETIME("DATETIME", "8字节，混合日期时间值，1000-01-01 00:00:00～9999:12:31 23:59:59，YYYY-MM-DD hh:mm:ss"),
    TIMESTAMP("TIMESTAMP", "4字节，时间戳，UTC 1970-01-01 00:00:01～2038:01:19 03:14:07，YYYY-MM-DD hh:mm:ss"),
    ;

    private String type;

    private String desc;

    MySqlColumnType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
