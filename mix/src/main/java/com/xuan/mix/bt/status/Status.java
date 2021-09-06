package com.xuan.mix.bt.status;

/**
 * 检查指标的状态
 * <p>
 * Created by xuan on 17/7/29.
 */
public class Status {

    /**
     * 状态级别
     */
    private final Level level;
    /**
     * 状态消息
     */
    private final String message;
    /**
     * 描述
     */
    private final String description;
    
    public Status(Level level){
        this(level, null, null);
    }

    public Status(Level level, String message){
        this(level, message, null);
    }
    
    public Status(Level level, String message, String description){
        this.level = level;
        this.message = message;
        this.description = description;
    }
    
    public Level getLevel() {
        return level;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Status{" +
                "level=" + level +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}