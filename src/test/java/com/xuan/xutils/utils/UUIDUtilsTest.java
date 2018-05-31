package com.xuan.xutils.utils;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xuan on 17/6/7.
 */
public class UUIDUtilsTest {

    @Test
    public void testUuid() {
        Set<String> idSet = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 30000; i++) {
                        String uuid = UUIDUtils.uuid();
                        if (idSet.contains(uuid)) {
                            System.out.println("++++++++++uuid重复:" + uuid);
                        }
                        //System.out.println("++++++++++uuid:" + uuid);
                    }
                }
            }).start();
        }
    }

}
