package com.xuan.xutils.utils;

import com.xuan.xutils.utils.domain.SonUser;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuan on 17/8/1.
 */
public class XBeanUtilsTest {

    @Test
    public void testMap2Bean() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "xuan");
        map.put("age", 18);
        map.put("sonName", "xuanson");
        SonUser user = XBeanUtils.map2Bean(map, SonUser.class);
        System.out.println("++++++++++user:" + user.getName() + "---" + user.getAge() + "---" + user.getSonName());
    }

    @Test
    public void testBean2Map(){
        SonUser user = new SonUser();
        user.setName("xuan");
        user.setAge(20);
        user.setSonName("xuanson");
        Map map = XBeanUtils.bean2Map(user);
        System.out.println("++++++++++map:" + map);
    }

}
