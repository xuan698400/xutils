package com.xuan.moho.base.log;

/**
 * @author xuan
 * @since 2023/5/12
 */
public class ObjTest {

    private String name;

    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

}
