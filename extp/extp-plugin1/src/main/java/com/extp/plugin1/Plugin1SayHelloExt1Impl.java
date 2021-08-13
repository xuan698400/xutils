package com.extp.plugin1;

import com.extp.platform.sdk.SayHelloExt1;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class Plugin1SayHelloExt1Impl implements SayHelloExt1 {
    @Override
    public String getMyName() {
        return "plugin1_名字";
    }

}
