package com.xuan.xutils.sql.orm;

import com.xuan.xutils.sql.orm.core.CamelUtils;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/12
 */
public class CamelUtilsTest {

    @Test
    public void testUnderlineToCamel() {
        String case1 = CamelUtils.underlineToCamel("name");
        String case2 = CamelUtils.underlineToCamel("user_name");
        String case3 = CamelUtils.underlineToCamel("user_naMe");
        String case4 = CamelUtils.underlineToCamel("");
        String case5 = CamelUtils.underlineToCamel("name_");
        String case6 = CamelUtils.underlineToCamel("user_name_");
        String case7 = CamelUtils.underlineToCamel("_name_");
        String case8 = CamelUtils.underlineToCamel("_user_name_");
        System.out.println(case1);
        System.out.println(case2);
        System.out.println(case3);
        System.out.println(case4);
        System.out.println(case5);
        System.out.println(case6);
        System.out.println(case7);
        System.out.println(case8);
    }

}
