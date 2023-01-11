package com.xuan.moho.sql.orm;

import com.xuan.moho.sql.orm.core.JdbcUtils;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/12
 */
public class JdbcUtilsTest {

    @Test
    public void testUnderlineToCamel() {
        String case1 = JdbcUtils.underlineToCamel("name");
        String case2 = JdbcUtils.underlineToCamel("user_name");
        String case3 = JdbcUtils.underlineToCamel("user_naMe");
        String case4 = JdbcUtils.underlineToCamel("");
        String case5 = JdbcUtils.underlineToCamel("name_");
        String case6 = JdbcUtils.underlineToCamel("user_name_");
        String case7 = JdbcUtils.underlineToCamel("_name_");
        String case8 = JdbcUtils.underlineToCamel("_user_name_");
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
