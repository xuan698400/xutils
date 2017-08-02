package com.xuan.xutils.domain.result;

/**
 * 结果状态码的枚举
 * <p>
 * Created by xuan on 17/8/2.
 */
public enum CodeEnum {
    ERROR(0, "错误"), SUCCESS(1, "成功");

    private int    code;
    private String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 判断是否包含
     *
     * @param code
     * @return
     */
    public static boolean isContain(int code) {
        for (CodeEnum codeEnum : CodeEnum.values()) {
            if (codeEnum.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    /**
     * isContain取反
     *
     * @param code
     * @return
     */
    public static boolean isNotContain(int code) {
        return !isContain(code);
    }

    /**
     * 判断是否相同
     *
     * @param code
     * @return
     */
    public boolean isEq(int code) {
        return this.code == code;
    }

    /**
     * isEq取反
     *
     * @param code
     * @return
     */
    public boolean isNotEq(int code) {
        return !isEq(code);
    }

    /**
     * 根据code获取枚举,如果获取不到,返回null
     *
     * @param code
     * @return
     */
    public CodeEnum codeOf(int code) {
        for (CodeEnum codeEnum : CodeEnum.values()) {
            if (codeEnum.getCode() == code) {
                return codeEnum;
            }
        }
        return null;
    }

}
