package com.xuan.xutils.base.utils;

import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/13
 */
public class PasswordHashTest {

    @Test
    public void test() {
        String password = "12345";
        String salt = PasswordHash.createSalt();
        String hash = PasswordHash.createHash("12345", salt);
        System.out.println("password:" + password);
        System.out.println("salt:" + salt);
        System.out.println("hash:" + hash);

        System.out.println("--------------------");

        System.out.println("加密后的密码：" + PasswordHash.getPasswordByHash(hash));
        System.out.println("salt：" + PasswordHash.getSaltByHash(hash));
        System.out.println(PasswordHash.validatePassword("12345",
            "1000:599c994a4c3dca8700f7b8c3cdfbad1c4420d42287bb52db:37bc1295c1afa85a4e54acfe9c878e7e3869f6df21bba4a7"));

    }
}
