package com.xuan.spi2.app2;

import com.xuan.spi2.platform.sdk.BlankTestExtensionPointsGroup;
import com.xuan.spi2.platform.sdk.TestExtensionPoints1;
import com.xuan.spi2.platform.sdk.TestExtensionPoints2;
import com.xuan.spi2.platform.sdk.TestExtensionPoints3;

/**
 * @author xuan
 * @since 2020/11/16
 */
public class App2TestExtensionPointsGroup extends BlankTestExtensionPointsGroup {

    @Override
    public TestExtensionPoints1 getTestExtensionPoints1() {
        return () -> {
            System.out.println("app2:execute1");
        };
    }

    @Override
    public TestExtensionPoints2 getTestExtensionPoints2() {
        return () -> {
            System.out.println("app2:execute2");
        };
    }

    @Override
    public TestExtensionPoints3 getTestExtensionPoints3() {
        return () -> {
            System.out.println("app2:execute3");
        };
    }

}
