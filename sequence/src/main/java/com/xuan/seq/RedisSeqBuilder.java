package com.xuan.seq;

import com.xuan.seq.range.impl.redis.RedisSeqRangeMgr;
import com.xuan.seq.sequence.Sequence;
import com.xuan.seq.sequence.impl.DefaultRangeSequence;

/**
 * 基于redis取步长，序列号生成器构建者
 *
 * @author xuan
 * @date 2018/5/30
 */
public class RedisSeqBuilder implements SeqBuilder {

    /**
     * 连接redis的IP[必选]
     */
    private String ip;
    /**
     * 连接redis的port[必选]
     */
    private int port;
    /**
     * 业务名称[必选]
     */
    private String bizName;
    /**
     * 认证权限，看redis是否配置了需要密码auth[可选]
     */
    private String auth;
    /**
     * 获取range步长[可选，默认：1000]
     */
    private int step = 1000;

    /**
     * 序列号分配起始值[可选：默认：0]
     */
    private long stepStart = 0;

    @Override
    public Sequence build() {
        //利用Redis获取区间管理器
        RedisSeqRangeMgr redisSeqRangeMgr = new RedisSeqRangeMgr();
        redisSeqRangeMgr.setIp(this.ip);
        redisSeqRangeMgr.setPort(this.port);
        redisSeqRangeMgr.setAuth(this.auth);
        redisSeqRangeMgr.setStep(this.step);
        redisSeqRangeMgr.setStepStart(stepStart);
        redisSeqRangeMgr.init();
        //构建序列号生成器
        DefaultRangeSequence sequence = new DefaultRangeSequence();
        sequence.setName(this.bizName);
        sequence.setSeqRangeMgr(redisSeqRangeMgr);
        return sequence;
    }

    public static RedisSeqBuilder create() {
        RedisSeqBuilder builder = new RedisSeqBuilder();
        return builder;
    }

    public RedisSeqBuilder ip(String ip) {
        this.ip = ip;
        return this;
    }

    public RedisSeqBuilder port(int port) {
        this.port = port;
        return this;
    }

    public RedisSeqBuilder auth(String auth) {
        this.auth = auth;
        return this;
    }

    public RedisSeqBuilder step(int step) {
        this.step = step;
        return this;
    }

    public RedisSeqBuilder bizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public RedisSeqBuilder stepStart(long stepStart) {
        this.stepStart = stepStart;
        return this;
    }

}
