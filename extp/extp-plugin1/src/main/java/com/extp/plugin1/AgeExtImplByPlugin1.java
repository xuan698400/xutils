package com.extp.plugin1;

import com.extp.platform.sdk.AgeExt;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class AgeExtImplByPlugin1 implements AgeExt {
    @Override
    public String getAge() {
        return "plugin1_19";
    }

}
