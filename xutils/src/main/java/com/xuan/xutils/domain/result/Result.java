package com.xuan.xutils.domain.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果
 * <p>
 * Created by xuan on 17/8/2.
 */
public class Result extends BaseResult {
    private static final long serialVersionUID = 1L;

    /**
     * 构建结果
     *
     * @param codeEnum 结果状态码枚举
     * @param bizCode  业务码
     * @param message  提示
     * @param result   结果对象
     * @return
     */
    public static Result obtain(CodeEnum codeEnum, String bizCode, String message, Object result) {
        Result r = new Result();
        r.setCode(codeEnum.getCode());
        r.setBizCode(bizCode);
        r.setMessage(message);
        if (null == result) {
            result = new HashMap<>();
        }
        r.setResult(result);
        return r;
    }

    /**
     * 成功
     *
     * @param message
     * @param result
     * @return
     */
    public static Result obtainSuccess(String message, Object result) {
        return Result.obtain(CodeEnum.SUCCESS, null, message, result);
    }

    /**
     * 成功
     *
     * @param result
     * @return
     */
    public static Result obtainSuccess(Object result) {
        return Result.obtainSuccess("成功", result);
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result obtainSuccess() {
        return Result.obtainSuccess(null);
    }

    /**
     * 成功
     *
     * @param keyList
     * @param valList
     * @return
     */
    public static Result obtainSuccessForKv(List<String> keyList, List<Object> valList) {
        if (null == keyList) {
            throw new NullPointerException("keyList is null.");
        }
        if (null == valList) {
            throw new NullPointerException("valList is null.");
        }
        if (keyList.size() == valList.size()) {
            throw new RuntimeException("keyList.size is not equals valList.size.");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0, n = valList.size(); i < n; i++) {
            if (valList.get(i) == null) {
                map.put(keyList.get(i), new HashMap<>());
            } else {
                map.put(keyList.get(i), valList.get(i));
            }
        }
        return Result.obtainSuccess(map);
    }

    /**
     * 成功
     *
     * @param key
     * @param value
     * @return
     */
    public static Result obtainSuccessForKv(String key, Object value) {
        List<String> keyList = new ArrayList<>();
        keyList.add(key);
        List<Object> valList = new ArrayList<>();
        valList.add(value);
        return obtainSuccessForKv(keyList, valList);
    }

    /**
     * 成功
     *
     * @param list
     * @return
     */
    public static Result obtainSuccessForList(List list) {
        return obtainSuccessForKv("list", list);
    }

    /**
     * 失败
     *
     * @param bizCode
     * @param message
     * @return
     */
    public static Result obtainError(String bizCode, String message) {
        return Result.obtain(CodeEnum.ERROR, bizCode, message, new HashMap<>());
    }

    /**
     * 失败
     *
     * @param message
     * @return
     */
    public static Result obtainError(String message) {
        return Result.obtainError(null, message);
    }

}
