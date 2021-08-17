package com.extp.plugin2;

import com.extp.platform.sdk.AgeExt;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class AgeExtImplByPlugin2 implements AgeExt {
    @Override
    public String getAge() {
        return "plugin2_19";
    }

}
