package com.extp.plugin2;

import com.extp.platform.sdk.SayHelloExt1;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class Plugin2SayHelloExt1Impl implements SayHelloExt1 {
    @Override
    public String getMyName() {
        return "plugin2_名字";
    }

}
