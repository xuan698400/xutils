package com.xuan.mix.concurrent.batchtask.test;

/**
 * @author xuan
 * @since 2021/9/10
 */
public class TargetClass {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TargetClass{" +
            "name='" + name + '\'' +
            '}';
    }
}
