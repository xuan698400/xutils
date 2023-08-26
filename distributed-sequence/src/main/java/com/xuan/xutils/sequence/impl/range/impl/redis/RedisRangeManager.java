package com.xuan.xutils.sequence.impl.range.impl.redis;

import com.xuan.xutils.sequence.SequenceException;
import com.xuan.xutils.sequence.impl.range.Range;
import com.xuan.xutils.sequence.impl.range.RangeManager;
import redis.clients.jedis.Jedis;

/**
 * 使用Redis实现序列号区间
 *
 * @author xuan
 * @date 2018/5/8
 */
public class RedisRangeManager implements RangeManager {

    /**
     * 前缀防止key重复
     */
    private final static String KEY_PREFIX = "xutils_sequence_";

    /**
     * redis客户端
     */
    private Jedis jedis;

    /**
     * IP
     */
    private String ip;
    /**
     * PORT
     */
    private Integer port;

    /**
     * 验证权限
     */
    private String auth;

    /**
     * 区间步长
     */
    private int step = 1000;

    /**
     * 区间起始位置，真实从stepStart+1开始
     */
    private long stepStart = 0;

    /**
     * 标记业务key是否存在，如果false，在取nextRange时，会取check一把
     * 这个boolean只为提高性能，不用每次都取redis check
     */
    private volatile boolean keyAlreadyExist;

    @Override
    public Range nextRange(String name) throws SequenceException {
        if (!keyAlreadyExist) {
            Boolean isExists = jedis.exists(getRealKey(name));
            if (!isExists) {
                //第一次不存在，进行初始化,setnx不存在就set，存在就忽略
                jedis.setnx(getRealKey(name), String.valueOf(stepStart));
            }
            keyAlreadyExist = true;
        }

        Long max = jedis.incrBy(getRealKey(name), step);
        long min = max - step + 1;
        return new Range(min, max);
    }

    @Override
    public void init() {
        checkParam();
        jedis = new Jedis(ip, port);
        if (null != auth) {
            jedis.auth(auth);
        }
    }

    private void checkParam() {
        if (isEmpty(ip)) {
            throw new SecurityException("Redis sequence range ip is empty.");
        }
        if (null == port) {
            throw new SecurityException("Redis sequence range port is null.");
        }
    }

    private String getRealKey(String name) {
        return KEY_PREFIX + name;
    }

    private boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public long getStepStart() {
        return stepStart;
    }

    public void setStepStart(long stepStart) {
        this.stepStart = stepStart;
    }
}
