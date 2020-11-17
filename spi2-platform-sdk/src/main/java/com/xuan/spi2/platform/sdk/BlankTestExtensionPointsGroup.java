package com.xuan.spi2.platform.sdk;

/**
 * @author xuan
 * @since 2020/11/16
 */
public class BlankTestExtensionPointsGroup implements TestExtensionPointsGroup {

    @Override
    public TestExtensionPoints1 getTestExtensionPoints1() {
        return null;
    }

    @Override
    public TestExtensionPoints2 getTestExtensionPoints2() {
        return null;
    }

    @Override
    public TestExtensionPoints3 getTestExtensionPoints3() {
        return null;
    }
}
