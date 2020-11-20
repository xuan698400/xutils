package com.xuan.garden.common;

/**
 * @author xuan
 * @since 2020/11/19
 */
public class ResultBuilder {

    public static <E> Result<E> obtainError(String code, String msg) {
        Result<E> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <E> Result<E> obtainSuccess() {
        Result<E> result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    public static <E> Result<E> obtainSuccess(E data) {
        Result<E> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

}
