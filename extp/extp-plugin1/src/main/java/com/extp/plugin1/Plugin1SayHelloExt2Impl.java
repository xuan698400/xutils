package com.extp.plugin1;

import com.extp.platform.sdk.SayHelloExt2;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class Plugin1SayHelloExt2Impl implements SayHelloExt2 {
    @Override
    public String getMyAge() {
        return "plugin1_19";
    }

}
