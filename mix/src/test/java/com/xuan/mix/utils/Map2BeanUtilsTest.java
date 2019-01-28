package com.xuan.mix.utils;

import com.xuan.mix.utils.domain.Family;
import com.xuan.mix.utils.domain.Father;
import com.xuan.mix.utils.domain.Son;
import com.xuan.mix.utils.domain.Son2;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuan on 17/8/1.
 */
public class Map2BeanUtilsTest {

    @Test
    public void testMap2Bean() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "xuan");
        map.put("age", 18);
        map.put("sonName", "xuanson");
        Son user = Map2BeanUtils.map2Bean(map, Son.class);
        System.out.println("++++++++++user:" + user.getName() + "---" + user.getAge() + "---" + user.getSonName());
    }

    @Test
    public void testBean2Map(){
        Son user = new Son();
        user.setName("xuan");
        user.setAge(20);
        user.setSonName("xuanson");
        Map map = Map2BeanUtils.bean2Map(user);
        System.out.println("++++++++++map:" + map);
    }

    @Test
    public void testBean2Map2(){
        Son son = new Son();
        son.setSonName("sonName");
        son.setAge(20);
        son.setName("name");

        Son2 son2 = new Son2();
        son2.setSon2Name("son2Name");
        son2.setAge(22);
        son2.setName("name2");

        Father father = new Father();
        father.setAge(88);
        father.setName("name3");

        Family family = new Family();
        family.setSon(son);
        family.setSon2(son2);
        family.setFather(father);

        Map map = Map2BeanUtils.bean2Map(family);
        System.out.println("++++++++++map:" + map);
    }

}
